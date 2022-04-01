package ru.nsu.sartakov.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Class for working with shared queue
 * @param <T> - type of stored objects
 */
public class SharedQueue<T> {
    private final int size;
    private final Deque<T> queue;

    /**
     * Constructor for SharedQueue
     * @param size - max size of queue
     */
    public SharedQueue(int size) {
        this.size = size;
        this.queue = new ArrayDeque<>();
    }

    /**
     * Check whether the queue is empty
     * @return true if queue is empty
     */
    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Returns the number of objects in the queue
     * @return amount of objects in the queue
     */
    public synchronized int getSize() {
        return this.queue.size();
    }

    /**
     * Puts an object in a queue
     * @param object - adding object
     * @throws NullPointerException if the object is null
     * @throws InterruptedException - if exception occurred
     */
    public synchronized void put(T object) throws NullPointerException, InterruptedException {
        if (object == null) {
            throw new NullPointerException();
        }
        while (queue.size() == size) {
            wait();
        }
        queue.push(object);
        notifyAll();
    }

    /**
     * Pop the object from queue
     * @return object from queue
     * @throws InterruptedException if exception occurred
     */
    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T result = queue.pop();
        notifyAll();
        return result;
    }

    /**
     * Pop multiple objects from queue
     * @param amount - amount of objects needed
     * @return list of objects
     * @throws IllegalArgumentException - if amount < 1 or amount > amount of objects in queue
     * @throws InterruptedException if exception occurred
     */
    public synchronized List<T> get(int amount) throws IllegalArgumentException, InterruptedException {
        if (amount < 1 || amount > size) {
            throw new IllegalArgumentException();
        }
        while (queue.isEmpty()) {
            wait();
        }
        List<T> result = new ArrayList<>();
        while (!queue.isEmpty() && result.size() != amount) {
            result.add(queue.pop());
        }
        return result;
    }

}
