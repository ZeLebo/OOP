package ru.nsu.sartakov;

import java.util.Arrays;

public class StreamChecker {
    public static boolean streamRun(Long[] array) {
        return Arrays.asList(array).
                parallelStream().
                noneMatch(Checker::isPrime);
    }
}
