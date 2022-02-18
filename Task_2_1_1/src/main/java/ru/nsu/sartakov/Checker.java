package ru.nsu.sartakov;

public class Checker {
    public static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        for (int i = 3; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
