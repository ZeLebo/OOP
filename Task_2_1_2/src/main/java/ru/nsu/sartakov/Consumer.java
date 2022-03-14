package ru.nsu.sartakov;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{
    private final List<Integer> tasks;

    private final int WORK_TIME = 1;

    public Consumer(List<Integer> sharedTasks) {
        tasks = sharedTasks;
    }

    @Override
    public void run() {
        System.out.println("Consumer start");
        while (true) {
            try {
                consume();
            } catch (InterruptedException ignored) {}
        }
    }

    private void consume() throws InterruptedException {
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                System.out.println("Storage is empty " + Thread.currentThread().getName() + " is waiting");
                tasks.wait();
            }
            Thread.sleep(TimeUnit.SECONDS.toMillis(WORK_TIME));
            int i = tasks.remove(0);
            System.out.println("Consumed: " + i);
            tasks.notifyAll();
        }
    }
}
