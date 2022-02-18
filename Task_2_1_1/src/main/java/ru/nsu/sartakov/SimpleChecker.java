package ru.nsu.sartakov;

public class SimpleChecker {
    public boolean sequentRun(long[] array) {
        for (long l : array) {
            if (Checker.isPrime(l)) {
                return true;
            }
        }
        return false;
    }
}
