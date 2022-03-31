package ru.nsu.sartakov.employee;

import ru.nsu.sartakov.interfaces.Producer;
import ru.nsu.sartakov.order.Order;
import ru.nsu.sartakov.order.OrderStatus;
import ru.nsu.sartakov.pizzeria.Pizzeria;
import ru.nsu.sartakov.queue.SharedQueue;

import static java.lang.Thread.sleep;


public class Baker implements Runnable, Producer<Order> {
    private final int id;
    private final int cookingTime;
    private final SharedQueue<Order> queue;
    private final SharedQueue<Order> storage;
    private boolean isRunning;

    public Baker(int id, int cookingTime, SharedQueue<Order> queue, SharedQueue<Order> storage){
        this.id = id;
        this.cookingTime = cookingTime;
        this.queue = queue;
        this.storage = storage;
    }

    public void stop() {
        this.isRunning = false;
    }

    public int getId() {
        return this.id;
    }

    public Order takeOrder() {
        try {
            Order order = queue.get();
            order.setStatus(OrderStatus.COOKING);
            return order;
        } catch (InterruptedException ignored) {
            return null;
        }
    }

    public void produce(Order order) {
        try {
            Thread.sleep(this.cookingTime);
            order.setStatus(OrderStatus.COOKED);
            storage.put(order);
        } catch (NullPointerException | InterruptedException ignored) {}
    }

    public void work() {
        Order order = takeOrder();
        if (order == null) {
            stop();
        }
        produce(order);
    }

    @Override
    public void run() {
        this.isRunning = true;
        while(this.isRunning) {
            work();
        }
    }
}
