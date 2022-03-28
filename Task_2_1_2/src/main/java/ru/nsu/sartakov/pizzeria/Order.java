package ru.nsu.sartakov.pizzeria;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Order {
    private static int nextId = 0;
    private final int id;
    private final String pizza;

    public Order(String pizza) {
        this.id = nextId++;
        this.pizza = pizza;
    }

    public String pizza() {
        return this.pizza;
    }

    public int id() {
        return this.id;
    }

    public String toString(String ...status) {
        return String.format(
                "[Order %s] [%s] %s",
                id,
                status[0],
                Arrays.stream(status).skip(1).collect(Collectors.joining(" "))
        );
    }
}
