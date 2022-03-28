package ru.nsu.sartakov.employee;

import ru.nsu.sartakov.entities.DeliveryEntity;
import ru.nsu.sartakov.pizzeria.Order;
import ru.nsu.sartakov.pizzeria.StoreHouse;

import static java.lang.Thread.sleep;

public class Deliver implements Runnable {
    private final StoreHouse storage;
    private final DeliveryEntity entity;

    public Deliver(StoreHouse storage, DeliveryEntity entity) {
        this.storage = storage;
        this.entity = entity;
    }

    @Override
    public void run() {
        while (true) {
            Order order = storage.takePizza();
            ship(order);
        }
    }

    private void ship(Order order) {
        long deliveryTimeMs = Math.round(Math.random() * 10) * 1000;
        storage.info(order.toString("in delivery", deliveryTimeMs + "ms"));
        try {
            sleep(deliveryTimeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storage.info(order.toString("delivered"));
    }
}
