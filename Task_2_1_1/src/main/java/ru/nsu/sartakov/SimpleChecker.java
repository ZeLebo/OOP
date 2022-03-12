package ru.nsu.sartakov;

public class SimpleChecker {
    /**
     * realisation of sequential checking for prime numbers
     * @param array - set of numbers needed to be checked
     * @return false if no prime numbers detected
     */
    public static boolean sequentRun(long[] array) {
        for (long l : array) {
            if (Checker.notPrime(l)) {
                return true;
            }
        }
        return false;
    }
}