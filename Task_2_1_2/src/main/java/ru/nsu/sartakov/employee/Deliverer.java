package ru.nsu.sartakov.employee;

import ru.nsu.sartakov.interfaces.Consumer;
import ru.nsu.sartakov.order.Order;
import static ru.nsu.sartakov.order.Order.Status.*;
import ru.nsu.sartakov.queue.SharedQueue;

import java.util.List;

/**
 * The Deliverer class simulates the work of deliverer
 * Get the order from shared storage and deliver it to abstract customer
 */
public class Deliverer implements Runnable, Consumer<List<Order>> {
    private final int deliveryTime = 300;
    private final int id;
    private final int capacity;
    private List<Order> orders;
    private final SharedQueue<Order> storage;
    private boolean isRunning;

    /**
     * Constructor for class Deliverer
     * @param id - deliverer's id
     * @param capacity - capacity of Deliverer (how many order can it take at once)
     * @param storage - shared order storage
     */
    public Deliverer(int id, int capacity, SharedQueue<Order> storage){
        this.id = id;
        this.capacity = capacity;
        this.storage = storage;
    }

    /**
     * Stop the working process
     */
    public void stop() {
        this.isRunning = false;
    }

    /**
     * Method returning the id of deliverer
     * @return deliverer id
     */
    public int getId() {
        return this.id;
    }

    private void setOrdersState(Order.Status status) {
        for (Order order : orders) {
            order.setStatus(status);
        }
    }

    /**
     * Get finished order from shared storage
     * Reduces the shared storage
     * @return delivered orders
     */
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

    /**
     * Start a thread of deliverer
     * Method simulates working process
     * Till the shared storage is not empty, Deliverer will work (running)
     */
    @Override
    public void run() {
        this.isRunning = true;
        while(this.isRunning) {
            List<Order> orders = consume();
            if (orders == null) {
                stop();
            }
        }
    }
}
