package ru.nsu.sartakov.order;

import java.util.function.Consumer;

public enum OrderStatus {
    PENDING("Pending"),
    COOKING("Cooking"),
    COOKED("Cooked"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
