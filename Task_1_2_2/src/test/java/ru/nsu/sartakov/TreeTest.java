package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreeTest {

    @Test
    void add() {
        var element = "Hello";
        Tree tree = new Tree(element);
        tree.add(element);
        System.out.println(tree.size());
        element = "World";
        tree.add(element);
        System.out.println(tree.size());
        System.out.println(tree.contains("World"));
        tree.remove(element);
        System.out.println(tree.size());
        tree.remove("Hello");
        System.out.println(tree.size());
    }

    @Test
    void addParent() {

    }

    @Test
    void remove() {
    }

    @Test
    void contains() {

    }

    @Test
    void isEmpty() {

    }
}