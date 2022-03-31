package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.employee.Baker;
import ru.nsu.sartakov.order.Order;
import ru.nsu.sartakov.queue.SharedQueue;
import static ru.nsu.sartakov.order.Order.Status.*;

import java.util.ArrayList;
import java.util.List;

public class BakerTest {
    private SharedQueue<Order> queue;
    private SharedQueue<Order> storage;
    private List<Order> orderList;

    @BeforeEach
    public void init() throws InterruptedException {
        queue = new SharedQueue<>(10);
        storage = new SharedQueue<>(10);
        orderList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Order order = new Order("Test Pizza");
            orderList.add(order);
            queue.put(order);
        }
    }

    @Test
    public void bake() throws InterruptedException {
        Baker baker = new Baker(0, 1, queue, storage);
        orderList.forEach(baker::produce);
        Assertions.assertEquals(orderList.size(), storage.getSize());
        for (int i = 0; i < 10; i++) {
            Order done = storage.get();
            Assertions.assertEquals(COOKED, done.getStatus());
            Assertions.assertTrue(orderList.contains(done));
        }
    }

    @Test
    public void cooking() {
        Baker baker = new Baker(0, 1, queue, storage);
        for (int i = 0; i < orderList.size(); i++) {
            Order done = baker.takeOrder();
            Assertions.assertEquals(COOKING, done.getStatus());
            Assertions.assertTrue(orderList.contains(done));
        }
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    public void work() {
        Baker baker = new Baker(0, 1, queue, storage);
        for (int i = 0; i < 10; i++) {
            baker.work();
        }
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    public void multipleBakers() throws InterruptedException {
        List<Baker> bakers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bakers.add(new Baker(i, 1, queue, storage));
        }
        bakers.forEach(baker -> new Thread(baker).start());
        Thread.sleep(100);
        Assertions.assertEquals(orderList.size(), storage.getSize());
    }
}
