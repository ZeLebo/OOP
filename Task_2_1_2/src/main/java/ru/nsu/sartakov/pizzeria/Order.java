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

    public State getStatus() {
        return status;
    }

    public void setStatus(State newStatus) {
        this.status = newStatus;
        System.out.println(this);
    }

    public String toString() {
        return "[Order id: " + id + "]" +  "[" + getStatus() + "]";
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
}
