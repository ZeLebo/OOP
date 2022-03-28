package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.entities.BakerEntity;
import ru.nsu.sartakov.entities.DeliveryEntity;
import ru.nsu.sartakov.pizzeria.Order;
import ru.nsu.sartakov.pizzeria.Pizzeria;
import ru.nsu.sartakov.pizzeria.StoreHouse;

import java.io.IOException;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class PizzeriaTest {
    Logger log;
    @BeforeEach
    void init() {
        StoreHouse storage = new StoreHouse(100, log);
        Pizzeria pizzeria = new Pizzeria(100, log);
    }

    @Test
    void addCourier() throws InterruptedException {
        Pizzeria pizzeria = new Pizzeria(100, log);
        BakerEntity baker = new BakerEntity(0, 1);
        DeliveryEntity deliver = new DeliveryEntity(0, 1);
        var begin = pizzeria.orders.size();
        pizzeria.addOrder("ZhoRa");

        pizzeria.addBaker(baker);
        pizzeria.addDeliver(deliver);
        sleep(200);
        Assertions.assertEquals(pizzeria.orders.size(), begin);

    }

    @Test
    void addOrder() throws InterruptedException {
        Pizzeria pizzeria = new Pizzeria(100, log);
        BakerEntity baker = new BakerEntity(0, 1);
        DeliveryEntity deliver = new DeliveryEntity(0, 2);
        pizzeria.addBaker(baker);
        pizzeria.addDeliver(deliver);
        pizzeria.addOrder("ZhoRa");
        var begin = pizzeria.orders.size();
        sleep(100);
        Assertions.assertTrue(pizzeria.orders.size() < begin);
    }

    @Test
    void takeOrder() {
        Pizzeria pizzeria = new Pizzeria(20, log);
        String name = "ZhoRa";
        pizzeria.addOrder(name);
        Order order = pizzeria.takeOrder();

        Assertions.assertEquals(name, order.pizza());
    }
}
