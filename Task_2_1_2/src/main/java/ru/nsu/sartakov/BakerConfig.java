package ru.nsu.sartakov;

public class BakerConfig {
    private int id;
    private int cookingTime;

    BakerConfig(int id, int cookingTime) {
        this.cookingTime = cookingTime;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}
