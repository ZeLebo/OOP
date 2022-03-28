package ru.nsu.sartakov.collections;

import ru.nsu.sartakov.entities.DeliveryEntity;

import java.util.List;

public interface DeliveryCollection {
    DeliveryEntity createDelivery(int capacity);
    List<DeliveryEntity> findAll();
}
