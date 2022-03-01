package ru.nsu.sartakov;

import java.util.Arrays;

public class ThreadChecker {
    static int THREADS = Runtime.getRuntime().availableProcessors();
    static boolean noPrime = true;
    public long[] arr;

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

    public synchronized static void hasPrime() {
        noPrime = false;
    }

    class PrimeCheck implements Runnable {
        final int ID;

        public PrimeCheck(int i) {
            ID = i;
        }
        public void run() {
            for (long l : arr) {
                if (Checker.isPrime(l)) {
                    ThreadChecker.hasPrime();
                    break;
                }
            }
        }
    }
}
