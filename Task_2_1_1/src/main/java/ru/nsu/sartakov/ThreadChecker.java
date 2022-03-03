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
    public boolean threadRun(long[] array, int threadsNumber) throws InterruptedException {
        if (threadsNumber > 0 && threadsNumber < THREADS) {
            THREADS = threadsNumber;
        }
        Thread[] threads = new Thread[THREADS];
        arr = Arrays.copyOf(array, array.length);

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(new PrimeCheck(i));
            threads[i].start();
        }
        for (int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        return noPrime;
    }

    public synchronized void hasNonePrime() {
        noPrime = true;
    }

    class PrimeCheck implements Runnable {
// todo split to chunk (range) to check
        public void run() {
            for (long l : arr) { // todo THREADS times check array
                if (Checker.notPrime(l)) {
                    hasNonePrime();
                    break;
                }
            }
        }
    }


}
