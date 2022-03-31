package ru.nsu.sartakov.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import ru.nsu.sartakov.entities.BakerEntity;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class Json {
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    public String readFile(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            Scanner scanner = new Scanner(fr);
            String dump = scanner.useDelimiter("\\A").next();
            scanner.close();
            fr.close();
            return dump;
        } catch (IOException e) {
            return "[]";
        }
    }
}
