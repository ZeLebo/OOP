package ru.nsu.sartakov.json;

/**
 * Class for Baker Entity. Describes the JSON object
 */
public class BakerEntity {
    private int id;
    private int cookingTime;

    /**
     * Returns baker's id read from file
     * @return baker's id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns read from config baker's cooking time
     * @return baker's cooking time
     */
    public int getCookingTime() {
        return cookingTime;
    }
}
