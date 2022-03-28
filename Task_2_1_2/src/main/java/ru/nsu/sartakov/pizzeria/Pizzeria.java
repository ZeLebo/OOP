package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.employee.Baker;
import ru.nsu.sartakov.entities.BakerEntity;
import ru.nsu.sartakov.entities.DeliveryEntity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class Pizzeria {
    private final Logger log;
    public final Queue<Order> orders;

    private final StoreHouse storage;

    public Pizzeria(int capacity, Logger log) {
        this.log = log;
        this.orders = new LinkedList<>();
        this.storage = new StoreHouse(capacity, log);
    }

    public void addBaker(BakerEntity bakerSample) {
        Baker baker = new Baker(this, bakerSample);
        new Thread(baker).start();
    }

    public void addDeliver(DeliveryEntity deliverSample) {
        storage.addDeviver(deliverSample);
    }

    public void addOrder(String pizza) {
        Order order = new Order(pizza);
        synchronized (orders) {
            orders.add(order);
            orders.notify();
        }
    }

    public Order takeOrder() {
        synchronized (orders) {
            while (orders.isEmpty()) {
                try {
                    orders.wait();
                } catch (InterruptedException ignored) {}
            }
            return orders.poll();
        }
    }

    public void addPizza(Order pizza) {
        storage.addPizza(pizza);
    }

    public void info(String message) {
        log.info(message);
    }
}
