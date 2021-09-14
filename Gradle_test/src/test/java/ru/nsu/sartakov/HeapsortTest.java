package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapsortTest {
    private Heapsort heapsort;

    @BeforeEach
    void beforeEach() {
        System.out.println("HeapsortTestHeapsort.BeforeEach");
        heapsort = new Heapsort();
    }

    @Test
    void sort_empty_array() {
        int arr[] = {};
        heapsort.sort(arr);
        int expected[] = {};
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    void sort() {
        int arr[] = {};
        heapsort.sort(arr);
        int expected[] = {};
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    void sort_oneOut() {
        int arr[] = {1, 2, 3 ,18, 25};
        int expected[] = arr.clone();

        heapsort.sort(arr);
        Arrays.sort(expected);

        Assertions.assertArrayEquals(expected, arr);
    }
}