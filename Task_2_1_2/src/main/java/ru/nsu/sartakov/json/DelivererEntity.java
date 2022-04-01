package ru.nsu.sartakov.json;

/**
 * Class for Deliverer Entity. Describes JSON object
 */
public class DelivererEntity {
    private int id;
    private int capacity;

    /**
     * Returns deliverer's id from config
     * @return - deliverer's id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the capacity of deliverer
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }
}
