package ru.nsu.sartakov.entities;

public class DeliveryEntity {
    private final int id;
    private final int capacity;

    public DeliveryEntity(int id, int capacity) {
        this.capacity = capacity;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
