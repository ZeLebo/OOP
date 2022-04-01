package ru.nsu.sartakov.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Class for reading JSON file
 */
public class Json {
    private static final String DEFAULT = "pizzeriaConfig/config.json";
    private final File file;
    private final String fileName;
    private BufferedReader reader;

    /**
     * Constructor for Json class
     * Initialize the file with DEFAULT file name
     */
    public Json() {
        this.fileName = DEFAULT;
        this.file = new File(this.fileName);
    }

    /**
     * Constructor for Json class
     * Initialize the file with specific fie name
     * @param fileName - Name of config file
     */
    public Json(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    /**
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Opens the file, make new file if not exist
     * Opens the file for reading
     */
    public void open() {
        try {
            if (!this.file.exists()) {
                var res = this.file.createNewFile();
                if (!res) {
                    System.err.println("Cannot create new file");
                }
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

    /**
     * Reads the pizzeria config from JSON
     * @return the array of parsable configuration settings
     */
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

    /**
     * Closes the file buffered reader
     */
    public void close() {
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}