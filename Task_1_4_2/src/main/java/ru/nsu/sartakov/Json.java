package ru.nsu.sartakov;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import com.google.gson.*;

public class Json {
    private final String fileName = "Notes.json";
    transient DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm:ss");
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, typeOfT, context) ->
                            LocalDateTime.parse(json.getAsString(), dateTimeFormatter.withLocale(Locale.ENGLISH)))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                    (src, typeOfSrc, context) -> new JsonPrimitive(dateTimeFormatter.format(src)))
            .setPrettyPrinting().create();

// TODO maybe I should store them as an array, instead of list…
    public List<Note> readFromFile() throws IOException {
        FileReader reader = new FileReader(fileName);
        return Arrays.stream(gson.fromJson(reader, Note[].class)).toList();
    }

    public void writeToFile(List<Note> notes) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            gson.toJson(notes, fileWriter);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file hasn't been found");
        }
    }
}
