package ru.nsu.sartakov;

public class Checker {
    /**
     * simple checker for primeness
     * @param number needed to be checked for primeness
     * @return true if number is prime
     */
    public static boolean notPrime(long number) {
        if (number <= 1) {
            return true;
        } else if (number <= 3) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return true;
            }
        }
        return false;
    }
}