package ru.nsu.sartakov.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class SharedQueue<T> {
    private final int size;
    private final Deque<T> queue;

    public SharedQueue(int size) {
        this.size = size;
        this.queue = new ArrayDeque<>();
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public synchronized int getSize() {
        return this.queue.size();
    }

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

    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T result = queue.pop();
        notifyAll();
        return result;
    }

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
