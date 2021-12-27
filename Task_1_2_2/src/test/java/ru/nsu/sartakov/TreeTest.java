package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;
import java.util.List;

class TreeTest {
    private Tree<String> tree;
    @BeforeEach
    void init() {
        tree = new Tree<>();
    }

    @Test
    void add() {
        tree.add("Hello");
        Assertions.assertTrue(tree.contains("Hello"));
    }

    @Test
    void addParent() {
        tree.add("Hello");
        tree.add("mad");
        tree.add("Hello", "world");
        List<String> checkList = new ArrayList<>();
        checkList.add("Hello");
        checkList.add("world");
        checkList.add("mad");
        int i = 0;
        for (var node : tree) {
            Assertions.assertEquals(node, checkList.get(i++));
        }
    }

    @Test
    void remove() {
        tree.add("Hello");
        tree.add("New string");
        tree.add("New new string");
        tree.remove("New string");
        tree.remove("New new string");
        Assertions.assertEquals(tree.size(), 1);
    }

    @Test
    void contains() {
        Assertions.assertFalse(tree.contains("Hello"));
        tree.add("Hello");
        Assertions.assertTrue(tree.contains("Hello"));
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(tree.isEmpty());
        tree.add("Hello");
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void size() {
        tree.add("Hello");
        tree.add("Hello");
        tree.add("Hello");
        Assertions.assertEquals(tree.size(), 3);
    }
}