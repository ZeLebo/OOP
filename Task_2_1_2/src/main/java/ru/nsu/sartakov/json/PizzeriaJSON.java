package ru.nsu.sartakov.json;

public class PizzeriaJSON {
    private int queue;
    private int capacity;
    private BakerJSON[] bakers;
    private DelivererJSON[] deliverers;

    public int getQueue() {
        return queue;
    }

    public int getCapacity() {
        return capacity;
    }

    public BakerJSON[] getBakers() {
        return bakers;
    }

    public DelivererJSON[] getDeliverers() {
        return deliverers;
    }
}
