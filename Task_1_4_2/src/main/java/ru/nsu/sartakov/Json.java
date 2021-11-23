package ru.nsu.sartakov;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import com.google.gson.*;

public class Json {
    private String fileName = "Notes.json";
    //Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();


    public void understand(Note notes) {
        try {
            FileWriter fileWritter = new FileWriter(fileName);

            gson.toJson(notes, fileWritter);
            fileWritter.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is not here");
        }
    }

    public String testWrite(List<Note> notes) throws IOException {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(notes, writer);
        }
        return gson.toJson(notes);
    }

/*
    private void openFileToRead() {
        try {
            FileReader fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found");
        }
    }

    public void writeToFile(List<Note> notes) throws IOException {
        fileWriter = new FileWriter(fileName);
        String json = gson.toJson(notes, List.class);
        Files.write(Path.of(fileName), Collections.singleton(json));

        fileWriter.close();
    }

    public List<Note> readJson() throws IOException {
        openFileToRead();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String data = Files.readString(Path.of(fileName));
        Type type = new TypeToken<List<Note>>(){}.getType();
        return gson.fromJson(data, type);
    }

 */
}
