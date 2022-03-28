package ru.nsu.sartakov.json;

import ru.nsu.sartakov.collections.DeliveryCollection;
import ru.nsu.sartakov.entities.DeliveryEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonDeliverer extends Json implements DeliveryCollection {
    private static final String FILENAME = "pizzeriaConfig/delivery.txt";

    private List<DeliveryEntity> getDeliverers() {
        String data = readFile(FILENAME);
        List<DeliveryEntity> delivers = Arrays.asList(gson().fromJson(data, DeliveryEntity[].class));
        return new ArrayList<>(delivers);
    }

    private void setDelivers(List<DeliveryEntity> delivers) {
        String data = gson().toJson(delivers);
        writeFile(FILENAME, data);
    }

    @Override
    public DeliveryEntity createDelivery(int capacity) {
        int maxId = -1;
        List<DeliveryEntity> delivers  = getDeliverers();
        for (DeliveryEntity unit : delivers) {
            maxId = Math.max(maxId, unit.getId());
        }
        DeliveryEntity deliver = new DeliveryEntity(maxId + 1, capacity);
        delivers.add(deliver);
        setDelivers(delivers);
        return deliver;
    }

    @Override
    public List<DeliveryEntity> findAll() {
        return getDeliverers();
    }
}
