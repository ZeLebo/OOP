package ru.nsu.sartakov;

import java.util.Collections;
import java.util.List;

public class Employees {
    public List<Baker> bakers;
    private List<Delivery> deliveryWorkers;

    public int getBakersAmount() {
        return bakers.size();
    }

    public int getDeliveryAmount() {
        return deliveryWorkers.size();
    }

    public List<Baker> getBakers() {
        return Collections.unmodifiableList(bakers);
    }

    public List<Delivery> getDeliveryWorkers() {
        return Collections.unmodifiableList(deliveryWorkers);
    }
}
