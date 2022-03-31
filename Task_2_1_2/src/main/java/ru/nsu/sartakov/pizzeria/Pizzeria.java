package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.employee.Baker;
import ru.nsu.sartakov.employee.Deliverer;
import ru.nsu.sartakov.json.BakerJSON;
import ru.nsu.sartakov.json.DelivererJSON;
import ru.nsu.sartakov.json.PizzeriaJSON;
import ru.nsu.sartakov.order.Order;
import ru.nsu.sartakov.queue.SharedQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pizzeria implements Runnable {
    private boolean isRunning;
    private List<Baker> bakers;
    private List<Deliverer> delivers;
    private final SharedQueue<Order> queue;
    private final SharedQueue<Order> storage;

    public Pizzeria(PizzeriaJSON config) {
        this.isRunning = false;
        this.queue = new SharedQueue<>(config.getQueue());
        this.storage = new SharedQueue<>(config.getCapacity());
        setBakers(config.getBakers());
        setDelivers(config.getDeliverers());
    }

    private void setBakers(BakerJSON[] bakers) {
        Stream<BakerJSON> bakerJSONStream = Arrays.stream(bakers);
        this.bakers = bakerJSONStream.map(bakerJSON -> new Baker(bakerJSON.getId(), bakerJSON.getCookingTime(), this.queue, this.storage)).collect(Collectors.toCollection(ArrayList::new));
        // todo : check
        //this.bakers = bakerJSONStream.map(bakerJSON -> new Baker(bakerJSON.getId(), bakerJSON.getCookingTime(), this.queue, this.storage)).collect(Collectors.toList());
    }

    private void setDelivers(DelivererJSON[] delivers) {
        Stream<DelivererJSON> delivererJSONStream = Arrays.stream(delivers);
        this.delivers = delivererJSONStream.map(delivererJSON -> new Deliverer(delivererJSON.getId(), delivererJSON.getCapacity(), this.storage)).collect(Collectors.toCollection(ArrayList::new));
    }

    public void addOrder(Order order) {
        try {
            this.queue.put(order);
        } catch (InterruptedException ignored) {
            System.err.println("Cannot add the order");
        }
    }

    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        this.isRunning = true;
        ExecutorService bakersThread = Executors.newFixedThreadPool(bakers.size());
        ExecutorService deliverersThread = Executors.newFixedThreadPool(delivers.size());
        bakers.forEach(bakersThread::execute);
        delivers.forEach(deliverersThread::execute);

        while (isRunning && !bakersThread.isTerminated() && !deliverersThread.isTerminated()) {}

        if (bakersThread.isTerminated() || deliverersThread.isTerminated()) {
            this.isRunning = false;
            bakersThread.shutdown();
            deliverersThread.shutdown();
            stop();
        }
    }

}
