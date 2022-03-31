package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.entities.DeliveryEntity;
import ru.nsu.sartakov.pizzeria.Order;
import ru.nsu.sartakov.pizzeria.StoreHouse;

import java.util.List;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class StoreHouseTest {
    StoreHouse storage;
    Logger log;

    @BeforeEach
    void init() {
        storage = new StoreHouse(5, log);
    }

    @Test
    void takePizza() {
        storage.addPizza(new Order("ZhoRa"));
        List<Order> order = storage.takePizza(1);
        Assertions.assertEquals("ZhoRa", order.get(0).pizza());
    }

    @Test
    void addPizza() {
        int begin = storage.pizzas.size();
        DeliveryEntity deliver = new DeliveryEntity(0, 1);
        Order order = new Order("pizza");
        storage.addDeliverer(deliver);
        storage.addPizza(order);
        Assertions.assertTrue(storage.pizzas.size() > begin);
    }

    @Test
    void tagePizzaFromStorage() {
        DeliveryEntity deliver = new DeliveryEntity(0, 1);
        Order order = new Order("pizza");
        storage.addDeliverer(deliver);
        storage.addPizza(order);
        int begin = storage.pizzas.size();
        storage.takePizza(1);
        Assertions.assertTrue(begin > storage.pizzas.size());
    }
}
