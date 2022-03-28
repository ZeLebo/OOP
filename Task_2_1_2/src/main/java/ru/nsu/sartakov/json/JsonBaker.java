package ru.nsu.sartakov.json;

import ru.nsu.sartakov.collections.BakerCollection;
import ru.nsu.sartakov.entities.BakerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonBaker extends Json implements BakerCollection {
    private static final String FILENAME = "pizzeriaConfig/bakers.txt";

    private List<BakerEntity> getBakers(){
        String data = readFile(FILENAME);
        List <BakerEntity> bakers = Arrays.asList(gson().fromJson(data, BakerEntity[].class));
        return new ArrayList<>(bakers);
    }

    @Override
    public List<BakerEntity> findAll() {
        return getBakers();
    }

    @Override
    public BakerEntity createBaker(int cookingTime) {
        int maxId  = -1;
        List<BakerEntity> bakers = getBakers();
        for (BakerEntity unit : bakers) {
            maxId = Math.max(maxId, unit.getId());
        }
        BakerEntity baker = new BakerEntity(maxId + 1, cookingTime);
        bakers.add(baker);

        return baker;
    }
}
