package ru.nsu.sartakov.order;

public class Order {
    private static int nextId = 0;
    private final int id;
    private final String pizza;
    public Status status;


    public enum Status {
        PENDING {
            @Override
            public String toString() {
                return "Pending";
            }
        },
        COOKING {
            @Override
            public String toString() {
                return "Cooking";
            }
        },
        COOKED {
            @Override
            public String toString() {
                return "Cooked";
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[Order id: " + id + "]" + "[Pizza: " + pizza() + "]" +  "[" + status.toString() + "]";
    }
}
