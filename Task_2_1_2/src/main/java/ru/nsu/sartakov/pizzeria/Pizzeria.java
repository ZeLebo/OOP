package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.employee.Baker;
import ru.nsu.sartakov.employee.Deliverer;
import ru.nsu.sartakov.json.BakerEntity;
import ru.nsu.sartakov.json.DelivererEntity;
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

import static ru.nsu.sartakov.order.Order.Status.PENDING;

public class Pizzeria implements Runnable {
    private boolean isRunning;
    private List<Baker> bakers;
    private List<Deliverer> delivers;
    private final SharedQueue<Order> queue;
    private final SharedQueue<Order> storage;

    /**
     * Constructor for Pizzeria class
     * @param config - pizzeria configuration
     */
    public Pizzeria(PizzeriaJSON config) {
        this.isRunning = false;
        this.queue = new SharedQueue<>(config.getQueue());
        this.storage = new SharedQueue<>(config.getCapacity());
        setBakers(config.getBakers());
        setDelivers(config.getDeliverers());
    }

    private void setBakers(BakerEntity[] bakers) {
        Stream<BakerEntity> bakerJSONStream = Arrays.stream(bakers);
        this.bakers = bakerJSONStream.map(bakerJSON -> new Baker(bakerJSON.getId(), bakerJSON.getCookingTime(), this.queue, this.storage)).collect(Collectors.toCollection(ArrayList::new));
    }

    private void setDelivers(DelivererEntity[] delivers) {
        Stream<DelivererEntity> delivererJSONStream = Arrays.stream(delivers);
        this.delivers = delivererJSONStream.map(delivererJSON -> new Deliverer(delivererJSON.getId(), delivererJSON.getCapacity(), this.storage)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Method allows adding order to the shared queue
     * Updates the order status
     * @param order adding to shared queue
     */
    public void addOrder(Order order) {
        try {
            this.queue.put(order);
            order.setStatus(PENDING);
        } catch (InterruptedException ignored) {
            System.err.println("Cannot add the order");
        }
    }

    /**
     * Stop the pizzeria
     */
    public void stop() {
        this.isRunning = false;
    }

    /**
     * Launches the pizzeria with configuration
     */
    @Override
    public void run() {
        this.isRunning = true;
        ExecutorService bakersThread = Executors.newFixedThreadPool(bakers.size());
        ExecutorService deliverersThread = Executors.newFixedThreadPool(delivers.size());
        bakers.forEach(bakersThread::execute);
        delivers.forEach(deliverersThread::execute);

        if (bakersThread.isTerminated() || deliverersThread.isTerminated()) {
            this.isRunning = false;
            bakersThread.shutdown();
            deliverersThread.shutdown();
            stop();
        }
    }

}
