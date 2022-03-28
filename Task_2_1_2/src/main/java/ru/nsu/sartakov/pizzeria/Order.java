package ru.nsu.sartakov.pizzeria;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Order {
    private static int nextId = 0;
    private final int id;
    private final String pizza;
    public State status;

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

    public boolean updateStatus() {
        if (this.status.ordinal() + 1 <= State.values().length) {
            this.status = State.values()[this.status.ordinal() + 1];
            return true;
        }
        return false;
    }

    private enum State {
        PENDING {
            @Override
            public String toString() {
                return "pending";
            }
        },
        COCKING {
            @Override
            public String toString() {
                return "Cocking";
            }
        },
        COCKED {
            @Override
            public String toString() {
                return "Cocked";
            }
        },
        DELIVERING {
            @Override
            public String toString() {
                return "Delivering";
            }
        },
        DELIVERED {
            @Override
            public String toString() {
                return "Delivered";
            }
        }

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
