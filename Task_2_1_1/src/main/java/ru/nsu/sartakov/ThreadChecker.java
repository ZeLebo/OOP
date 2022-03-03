package ru.nsu.sartakov;

import java.util.*;
import java.util.concurrent.*;

public class ThreadChecker {
    int THREADS = Runtime.getRuntime().availableProcessors();
    private Deque<Long> numbersDeque;

    private synchronized Long getNumber() {
        return numbersDeque.isEmpty() ? null : numbersDeque.pop();
    }

    private Callable<Boolean> primeChecker() {
        Long number;
        while ((number = getNumber()) != null) {
            if (Checker.notPrime(number)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Runs the check-up using multi-thread
     * @param array - set of numbers, needed to be checked
     * @param threadsNumber - amount of threads, limited by the machine
     * @return true if it has no prime
     * @throws InterruptedException if the operations were interrupted
     */
    public boolean threadRun(long[] array, int threadsNumber) throws InterruptedException {
        if (threadsNumber > 0 && threadsNumber < THREADS) {
            THREADS = threadsNumber;
        }

        numbersDeque = new ArrayDeque<>(Arrays.stream(array).boxed().toList());
        Callable<Boolean> thread = primeChecker();
    }
}
