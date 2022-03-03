package ru.nsu.sartakov;

public class Checker {
    /**
     * simple checker for primeness
     * @param number needed to be checked for primeness
     * @return true if number is prime
     */
    public static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        for (int i = 3; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean notPrime(long number) {
        return !isPrime(number);
    }
}
