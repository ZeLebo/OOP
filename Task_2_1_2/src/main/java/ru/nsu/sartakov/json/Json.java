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
    System.Logger log;
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

/*
    public String readFile(String fileName) throws IOException {
        try {
            File file = new File(fileName);
            if (file.length() == 0) {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write("[]");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            log.log(System.Logger.Level.ERROR, "The file wasn't found", e);
            throw e;
        }

        try (FileReader reader = new FileReader(fileName)) {
            return Arrays.stream(gson().fromJson(reader, BakerEntity[].class)).toString();
        }
    }

 */



    public void writeFile(String fileName, String data) {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(data);
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
