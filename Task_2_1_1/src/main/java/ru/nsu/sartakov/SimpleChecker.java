package ru.nsu.sartakov;

public class SimpleChecker {
    public static boolean sequentRun(long[] array) {
        for (long l : array) {
            if (Checker.isPrime(l)) {
                return false;
            }
        }
        return true;
    }
}
