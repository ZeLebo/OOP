package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.employee.Deliverer;
import static ru.nsu.sartakov.order.Order.Status.*;
import ru.nsu.sartakov.order.Order;
import ru.nsu.sartakov.queue.SharedQueue;

import java.util.ArrayList;
import java.util.List;

public class DelivererTest {
    private SharedQueue<Order> storage;
    private List<Order> orderList;

    @BeforeEach
    public void init() throws InterruptedException {
        storage = new SharedQueue<>(10);
        orderList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Order order = new Order("Test Pizza");
            orderList.add(order);
            storage.put(order);
        }
    }

    @Test
    public void consume() {
        Deliverer deliverer = new Deliverer(0, 10, storage);
        while (!storage.isEmpty()) {
            List<Order> done = deliverer.consume();
        }
        Assertions.assertTrue(storage.isEmpty());
    }

    @Test
    public void status() {
        Deliverer deliverer = new Deliverer(0, 10, storage);
        while (!storage.isEmpty()) {
            List<Order> done = deliverer.consume();
            done.forEach(order -> Assertions.assertEquals(DELIVERED, order.getStatus()));
        }
    }

    @Test
    public void multipleDelivers() throws InterruptedException {
        List<Deliverer> deliverers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            deliverers.add(new Deliverer(i, 5, storage));
        }
        deliverers.forEach(deliverer -> new Thread(deliverer).start());
        Thread.sleep(100);
        deliverers.forEach(Deliverer::stop);
        Assertions.assertTrue(storage.isEmpty());
    }
}
