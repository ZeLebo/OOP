package ru.nsu.sartakov.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.nsu.sartakov.collections.BakerCollection;
import ru.nsu.sartakov.entities.BakerEntity;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonBaker extends Json implements BakerCollection {
    private static final String FILENAME = "pizzeriaConfig/bakers.json";

    private List<BakerEntity> getBakers(){
        Json myReader = new Json(FILENAME);
        List <BakerEntity> bakers = Arrays.asList(gson().fromJson(myReader.readFile(), BakerEntity[].class));
        return new ArrayList<>(bakers);
    }

    private Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
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
