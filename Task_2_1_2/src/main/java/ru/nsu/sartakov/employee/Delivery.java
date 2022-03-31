package ru.nsu.sartakov.employee;

import ru.nsu.sartakov.entities.DeliveryEntity;
import ru.nsu.sartakov.pizzeria.Order;
import ru.nsu.sartakov.pizzeria.StoreHouse;

import java.util.List;

import static java.lang.Thread.sleep;

public class Delivery implements Runnable {
    private final StoreHouse storage;
    private final DeliveryEntity entity;

    public Delivery(StoreHouse storage, DeliveryEntity entity) {
        this.storage = storage;
        this.entity = entity;
    }

    @Override
    public void run() {
        while (true) {
            List<Order> orders = storage.takePizza(entity.getCapacity());
            //orders.forEach(this::deliver);
            for (var order : orders) {
                this.deliver(order);
            }
        }
    }

    private void deliver(Order order) {
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
