package ru.nsu.sartakov;

import java.util.*;
import java.util.concurrent.*;

public class ThreadChecker {
    int THREADS = Runtime.getRuntime().availableProcessors();
    private Deque<Long> numbersDeque;

    private synchronized Long getNumber() {
        return numbersDeque.isEmpty() ? null : numbersDeque.pop();
    }

    /**
     * Runs the check-up using multi-thread
     * @param array - set of numbers, needed to be checked
     * @param threadsNumber - amount of threads, limited by the machine
     * @return true if it has no prime
     * @throws InterruptedException if the operations were interrupted
     */
    public boolean threadRun(long[] array, int threadsNumber) throws InterruptedException, ExecutionException {
        if (threadsNumber > 0 && threadsNumber < THREADS) {
            THREADS = threadsNumber;
        }
        numbersDeque = new ArrayDeque<>(Arrays.stream(array).boxed().toList());
        Callable<Boolean> thread = () -> {
            Long number;
            while((number = getNumber()) != null) {
                if(Checker.notPrime(number)) {
                    return true;
                }
            }
            return false;
        };

        List<Callable<Boolean>> threads = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            threads.add(thread);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        List<Future<Boolean>> futureList = executorService.invokeAll(threads);
        for (var fut : futureList) {
            if (fut.get()) {
                executorService.shutdown();
                return true;
            }
        }
        executorService.shutdownNow();
        return false;
    }
}
