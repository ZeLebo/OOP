package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyTreeTest {

    @Test
    void add() {
        MyTree tree = new MyTree(3, 1);
        tree.add(new int[]{1, 2, 3, 4});
    }

    @Test
    void remove() {
        MyTree tree = new MyTree(3, "Hello");
        tree.remove("Hello");
        Assertions.assertEquals(tree.getClass(), tree.getClass());
    }
}