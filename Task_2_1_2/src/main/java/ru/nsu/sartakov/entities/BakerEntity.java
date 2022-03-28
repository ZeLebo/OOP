package ru.nsu.sartakov.entities;

public class BakerEntity {
    private final int id;
    private final int cookingTime;
    
    public BakerEntity(int id, int cookingTime) {
        this.id = id;
        this.cookingTime = cookingTime;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getCookingTime() {
        return this.cookingTime;
    }
}
