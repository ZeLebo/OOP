package ru.nsu.sartakov.json;

/**
 * Class describing Pizzeria in JSON format
 */
public class PizzeriaJSON {
    private int queue;
    private int capacity;
    private BakerEntity[] bakers;
    private DelivererEntity[] deliverers;

    /**
     * Returns the size shared queue from config
     * @return queue size
     */
    public int getQueue() {
        return queue;
    }

    /**
     * Returns the storage size from config
     * @return capacity of storage
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the array of bakes configuration from JSON file
     * @return array of baker's data
     */
    public BakerEntity[] getBakers() {
        return bakers;
    }

    /**
     * Returns the array of deliverers configuration from JSON file
     * @return array of deliverers data
     */
    public DelivererEntity[] getDeliverers() {
        return deliverers;
    }
}
