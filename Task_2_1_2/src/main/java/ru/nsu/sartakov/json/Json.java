package ru.nsu.sartakov.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Json {
    private static final String DEFAULT = "config.json";
    private final File file;
    private final String fileName;
    private BufferedReader reader;

    public Json() {
        this.fileName = DEFAULT;
        this.file = new File(this.fileName);
    }

    public Json(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void open() {
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(this.file));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            content.append(currentLine).append("\n");
        }
        return content.toString();
    }

    public PizzeriaJSON read() {
        String content;
        try {
            content = readAllLines(reader);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }

        if (content.equals("")) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(content, PizzeriaJSON.class);
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}