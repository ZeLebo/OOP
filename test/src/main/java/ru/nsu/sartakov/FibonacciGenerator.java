package ru.nsu.sartakov;

public class FibonacciGenerator {

    long[] cache;

    public FibonacciGenerator (int elementsCount){
        if (elementsCount <= 0) {
            return;
        }
        cache = new long[elementsCount];
        cache[0] = 1;
        if (elementsCount > 1) {
            cache[1] = 1;
        }

    }


    public long getNthNumber(int n) {
        if (cache == null || n < 0 || n <= cache.length)
            if (cache[n] != 0) {
                return cache[n];
            } else {
                cache[n] = getNthNumber(n - 1) + getNthNumber(n - 2);
                return cache[n];
            }
    return 0;
    }
}
