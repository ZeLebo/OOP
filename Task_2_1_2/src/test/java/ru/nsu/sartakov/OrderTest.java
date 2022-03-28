package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.pizzeria.Order;

public class OrderTest {
    @Test
    void naming() {
        Order order = new Order("Margarita");
        Assertions.assertEquals("Margarita", order.pizza());
    }

    @Test
    void idTest() {
        Order order1 = new Order("order1");
        Order order2 = new Order("order2");
        Assertions.assertNotEquals(order1.id(), order2.id());
    }
}