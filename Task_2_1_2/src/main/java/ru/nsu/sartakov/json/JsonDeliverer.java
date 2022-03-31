package ru.nsu.sartakov.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.nsu.sartakov.collections.DeliveryCollection;
import ru.nsu.sartakov.entities.DeliveryEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonDeliverer extends Json implements DeliveryCollection {
    private static final String FILENAME = "pizzeriaConfig/delivery.json";

    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    private List<DeliveryEntity> getDeliverers() {
        Json myReader = new Json(FILENAME);
        List<DeliveryEntity> delivers = Arrays.asList(gson().fromJson(myReader.readFile(), DeliveryEntity[].class));
        return new ArrayList<>(delivers);
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
        return deliver;
    }

    @Override
    public List<DeliveryEntity> findAll() {
        return getDeliverers();
    }
}
