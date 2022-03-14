package ru.nsu.sartakov;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.sartakov.BakerConfig;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class JSONReader {
    List<BakerConfig> readBakers(File fileBakers) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(fileBakers, BakerConfig[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
