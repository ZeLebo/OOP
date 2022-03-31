package ru.nsu.sartakov.employee;

import ru.nsu.sartakov.interfaces.Consumer;
import ru.nsu.sartakov.order.Order;
import static ru.nsu.sartakov.order.Order.Status.*;
import ru.nsu.sartakov.queue.SharedQueue;

import java.util.List;

public class Deliverer implements Runnable, Consumer<List<Order>> {
    private final int deliveryTime = 300;
    private final int id;
    private final int capacity;
    private List<Order> orders;
    private final SharedQueue<Order> storage;
    private boolean isRunning;

    public Deliverer(int id, int capacity, SharedQueue<Order> storage){
        this.id = id;
        this.capacity = capacity;
        this.storage = storage;
    }

    public void stop() {
        this.isRunning = false;
    }

    public int getId() {
        return this.id;
    }

    private void setOrdersState(Order.Status status) {
        for (Order order : orders) {
            order.setStatus(status);
        }
    }

    @Override
    public List<Order> consume() {
        try {
            orders = storage.get(this.capacity);
            setOrdersState(DELIVERING);
            Thread.sleep(this.deliveryTime);
            setOrdersState(DELIVERED);
            return orders;
        } catch (InterruptedException ignored) {
            return null;
        }
    }

    public void work() {
        List<Order> orders = consume();
        if (orders == null) {
            stop();
        }
    }

    @Override
    public void run() {
        this.isRunning = true;
        while(this.isRunning) {
            work();
        }
    }
}
