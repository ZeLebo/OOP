package ru.nsu.sartakov;

import java.util.Arrays;

public class ThreadChecker {
    static int THREADS = Runtime.getRuntime().availableProcessors();
    static boolean noPrime = false;
    public static long[] arr;


    public static boolean threadRun(long[] array, int threadsNumber) throws InterruptedException {
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

    public static long[] getArr() {
        return arr;
    }

    public synchronized static void hasNoPrime() {
        noPrime = true;
    }

    static class PrimeCheck implements Runnable {
        final int ID;

        public PrimeCheck(int i) {
            ID = i;
        }
        public void run() {
            for (long l : ThreadChecker.getArr()) {
                if (l % ThreadChecker.THREADS == ID && Checker.isPrime(l)) {
                    ThreadChecker.hasNoPrime();
                    break;
                }
            }
        }
    }
}