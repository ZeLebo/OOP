package ru.nsu.sartakov.employee;

import ru.nsu.sartakov.entities.BakerEntity;
import ru.nsu.sartakov.entities.DeliveryEntity;
import ru.nsu.sartakov.pizzeria.Order;
import ru.nsu.sartakov.pizzeria.Pizzeria;
import ru.nsu.sartakov.pizzeria.StoreHouse;

import static java.lang.Thread.sleep;


public class Baker implements Runnable {
    private final Pizzeria pizzeria;
    private final BakerEntity entity;

    public Baker(Pizzeria pizzeria, BakerEntity entity) {
        this.pizzeria = pizzeria;
        this.entity = entity;
    }

    @Override
    public void run() {
        while (true) {
            Order order = pizzeria.takeOrder();
            bake(order);
            pizzeria.addPizza(order);
        }
    }

    private void bake(Order order) {
        // todo delete
        long bakeTime = 10000 / (entity.getCookingTime() < 2 ? 10 : entity.getCookingTime());
        pizzeria.info(order.setStatus(Order.setStatus(COOCKING)));
        try {
            sleep(bakeTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pizzeria.info(order.toString("ready"));
    }
}
