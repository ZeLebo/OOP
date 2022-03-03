package ru.nsu.sartakov;

import java.util.Arrays;

public class StreamChecker {
    /**
     * Prime checking using streams
     * @param array - set of numbers needed to be checked
     * @return false if no prime numbers are detected
     */
    public static boolean streamRun(Long[] array) {
        return Arrays.asList(array).
                parallelStream().
                anyMatch(Checker::notPrime);
    }
}
