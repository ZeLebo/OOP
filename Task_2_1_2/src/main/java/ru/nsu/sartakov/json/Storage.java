package ru.nsu.sartakov.json;

import ru.nsu.sartakov.collections.BakerCollection;
import ru.nsu.sartakov.collections.DeliveryCollection;

public class Storage {
    private int queueSize;
    private int storageCapacity;
    private BakerJSON[] bakers;
    private DelivererJSON[] deliverers;

    public int getQueueSize() {
        return queueSize;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public BakerJSON[] getBakers() {
        return bakers;
    }

    public DelivererJSON[] getDeliverers() {
        return deliverers;
    }
}
