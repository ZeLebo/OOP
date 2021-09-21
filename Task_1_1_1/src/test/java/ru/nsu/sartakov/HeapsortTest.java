package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapsortTest {
    public Heapsort heapsort;

    @BeforeEach
    void beforeEach() {
        heapsort = new Heapsort();
    }

    @Test
    void sortEmptyArray() {
        Integer[] arr = {};
        heapsort.sort(arr);
        Integer[] expected = {};
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    void sortNegativeArray() {
        Integer[] arr = {-1,-20,-25,-35,-46};
        heapsort.sort(arr);
        Integer[] expected = {-46, -35, -25, -20, -1};
        Assertions.assertArrayEquals(expected, arr);
    }
    @Test
    void sortBoarderArray() {
        Integer[] arr = {2,-1,1,0,-2};
        heapsort.sort(arr);
        Integer[] expected = {-2, -1, 0, 1, 2};
        Assertions.assertArrayEquals(expected, arr);
    }
    @Test
    void sortExampleArray() {
        Integer[] arr = {5,4,3,2,1};
        heapsort.sort(arr);
        Integer[] expected = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expected, arr);
    }
}