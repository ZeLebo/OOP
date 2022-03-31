package ru.nsu.sartakov.order;

public class Order {
    private static int nextId = 0;
    private final int id;
    private final String pizza;
    public OrderStatus status;

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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus newStatus) {
        this.status = newStatus;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[Order id: " + id + "]" +  "[" + getStatus() + "]";
    }
}
