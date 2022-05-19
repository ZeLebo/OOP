package ru.nsu.sartakov.task_2_2_1.settings;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;

public class SettingsLoader {
    // default filename
    private static final String DEFAULT_FILENAME = "./src/main/java/ru/nsu/sartakov/task_2_2_1/settings/settings.json";
    public int winningScore = 10;
    public int snakeSpeed = 5;

    public int foodAmount = 10;
    public int obstacleAmount = 5;

    public int width = 50;
    public int height = 50;
    public int cellSize = 25;

    public SettingsLoader() {}

    public SettingsLoader(int winningScore, int snakeSpeed, int foodAmount,
                          int obstacleAmount, int width, int height, int cellSize) {
        this.winningScore = winningScore;
        this.snakeSpeed = snakeSpeed;
        this.foodAmount = foodAmount;
        this.obstacleAmount = obstacleAmount;
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
    }
    // write to json file

    /**
     * Save settings to default file
     * @throws Exception
     */
    public void saveSettings() throws Exception {
        saveSettings(DEFAULT_FILENAME);
    }

    /**
     * Save settings to file
     * @param file
     * @throws Exception
     */
    public void saveSettings(String file) throws Exception {
        // create new JsonWriter
        JsonWriter writer = new JsonWriter(new FileWriter(file));
        writer.beginObject();
        // write with padding and new line
        writer.setIndent("  ");
        writer.name("winningScore").value(winningScore).setIndent(" ");
        writer.name("snakeSpeed").value(snakeSpeed).setIndent(" ");
        writer.name("foodAmount").value(foodAmount).setIndent(" ");
        writer.name("obstacleAmount").value(obstacleAmount).setIndent(" ");
        writer.name("width").value(width).setIndent("   ");
        writer.name("height").value(height).setIndent(" ");
        writer.name("cellSize").value(cellSize).setIndent(" ");
        writer.endObject();
        writer.close();
    }

    // read from json file
    public void loadSettings() throws Exception {
        loadSettings(DEFAULT_FILENAME);
    }

    public void loadSettings(String file) throws Exception {
        JsonReader reader = new JsonReader(new FileReader(file));
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "winningScore" -> winningScore = reader.nextInt();
                case "snakeSpeed" -> snakeSpeed = reader.nextInt();
                case "foodAmount" -> foodAmount = reader.nextInt();
                case "obstacleAmount" -> obstacleAmount = reader.nextInt();
                case "width" -> width = reader.nextInt();
                case "height" -> height = reader.nextInt();
                case "cellSize" -> cellSize = reader.nextInt();
                default -> reader.skipValue();
            }
        }
        reader.endObject();
    }
}


