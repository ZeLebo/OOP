package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.employee.Deliver;
import ru.nsu.sartakov.entities.DeliveryEntity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class StoreHouse {
    private final Logger log;
    private final Queue<Order> pizzas;
    private final int capacity;

    public StoreHouse(int capacity, Logger log) {
        this.log = log;
        this.capacity = capacity;
        this.pizzas = new LinkedList<>();
    }

    public void addDeviver(DeliveryEntity deliverSample) {
        Deliver deliver = new Deliver(this, deliverSample);
        new Thread(deliver).start();
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

    public Order takePizza() {
        synchronized (pizzas) {
            while (pizzas.isEmpty()) {
                try {
                    pizzas.wait();
                } catch (InterruptedException ignored) {}
            }
            pizzas.notify();
            return pizzas.poll();
        }
    }

    public void info(String message) {
        log.info(message);
    }
}
