package ru.nsu.sartakov.collections;

import ru.nsu.sartakov.entities.BakerEntity;

import java.util.List;

public interface BakerCollection {
    BakerEntity createBaker(int cookingTime);
    List<BakerEntity> findAll();
}
