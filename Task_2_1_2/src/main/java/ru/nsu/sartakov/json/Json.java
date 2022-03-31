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
    private BufferedReader reader;
    private String fileName;
    private final File file;

    Json() {
        file = null;
    }

    Json(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void open() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile() {
        String content;
        try {
            content = readAllLines(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (content.equals("")) {
            return null;
        }
        return content;
    }

    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String current;
        while((current = reader.readLine()) != null) {
            content.append(current).append("\n");
        }
        return content.toString();
    }


    public String readFile_(String fileName) {
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
