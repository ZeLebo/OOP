package ru.nsu.sartakov;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import com.google.gson.*;

public class Json {
    public String fileName = "Notes.json";
    Json (String fileName) {
        this.fileName = fileName;
    }
    Json () {}
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, typeOfT, context) ->
                            LocalDateTime.parse(json.getAsString(), dateTimeFormatter.withLocale(Locale.ENGLISH)))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                    (src, typeOfSrc, context) -> new JsonPrimitive(dateTimeFormatter.format(src)))
            .setPrettyPrinting().create();

    /**
     * @return list of all notes from the file
     * @throws IOException if the file is not exist
     */
    public List<Note> readFromFile() throws IOException {
        try {
            File file = new File(fileName);
            if (file.length() == 0) {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write("[]");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file wasn't found");
        }

        try (FileReader reader = new FileReader(fileName)) {
            return Arrays.stream(gson.fromJson(reader, Note[].class)).toList();
        }
    }

    /**
     * @param notes is a list on notes to write to file
     */
    public void writeToFile(List<Note> notes) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            gson.toJson(notes, fileWriter);

        } catch (FileNotFoundException e) {
            System.out.println("The file hasn't been found");
        }
    }
}
