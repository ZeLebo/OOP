package ru.nsu.sartakov.employee;

import ru.nsu.sartakov.interfaces.Producer;
import ru.nsu.sartakov.order.Order;
import static ru.nsu.sartakov.order.Order.Status.*;
import ru.nsu.sartakov.queue.SharedQueue;

/**
 * The baker class simulates the work of a baker
 * Get the order from shared queue, produce the order and put into shared storage
 */
public class Baker implements Runnable, Producer<Order> {
    private final int id;
    private final int cookingTime;
    private final SharedQueue<Order> queue;
    private final SharedQueue<Order> storage;
    private boolean isRunning;

    /**
     * Constructor for class Baker
     * @param id - baker's id
     * @param cookingTime - time of baking (working)
     * @param queue - shared order queue
     * @param storage - shared order storage (for Deliverer)
     */
    public Baker(int id, int cookingTime, SharedQueue<Order> queue, SharedQueue<Order> storage){
        this.id = id;
        this.cookingTime = cookingTime;
        this.queue = queue;
        this.storage = storage;
    }

    /**
     * Stop the working process
     */
    public void stop() {
        this.isRunning = false;
    }

    /**
     * Methods returning the id of baker
     * @return baker's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Take an order from shared queue
     * Reduces the shared queue
     * @return finished order
     */
    public Order takeOrder() {
        try {
            Order order = queue.get();
            order.setStatus(COOKING);
            return order;
        } catch (InterruptedException ignored) {
            return null;
        }
    }

    /**
     * Put finished order to shared storage
     * Increases the shared storage with finished order
     * @param order - finished order (object from shared queue)
     */
    @Override
    public void produce(Order order) {
        try {
            Thread.sleep(this.cookingTime);
            order.setStatus(COOKED);
            storage.put(order);
        } catch (NullPointerException | InterruptedException ignored) {}
    }

    /**
     * Start a thread of baker
     * Method simulates working process
     * Till there orders in shared queue, Baker will work (running)
     */
    @Override
    public void run() {
        this.isRunning = true;
        while(this.isRunning) {
            Order order = takeOrder();
            if (order == null) {
                stop();
            }
            produce(order);
        }
    }
}
