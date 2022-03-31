package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.employee.Delivery;
import ru.nsu.sartakov.entities.DeliveryEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class StoreHouse {
    private final Logger log;
    public final Queue<Order> pizzas;
    private final int capacity;
    private final List<Thread> deliverThreads;

    public StoreHouse(int capacity, Logger log) {
        this.log = log;
        this.capacity = capacity;
        this.pizzas = new LinkedList<>();
        this.deliverThreads = new ArrayList<>();
    }

    public void addDeliverer(DeliveryEntity deliverSample) {
        Delivery deliver = new Delivery(this, deliverSample);
        Thread deliverThread = new Thread(deliver);
        deliverThreads.add(deliverThread);
        deliverThread.start();
    }

    public void addPizza(Order pizza) {
        synchronized (pizzas) {
            while (pizzas.size() >= capacity) {
                try {
                    pizzas.wait();
                } catch (InterruptedException ignored) {}
            }
            pizzas.add(pizza);
            pizzas.notify();
        }
    }

    public List<Order> takePizza(int maxAmount) {
        synchronized (pizzas) {
            while (pizzas.isEmpty()) {
                try {
                    pizzas.wait();
                } catch (InterruptedException ignored) {}
            }
            pizzas.notify();

            List<Order> pizzasInDelivery = new ArrayList<>();
            while (pizzasInDelivery.size() < maxAmount && !pizzas.isEmpty()) {
                pizzasInDelivery.add(pizzas.poll());
            }
            return pizzasInDelivery;
        }
    }

    public void info(String message) {
        log.info(message);
    }
}
