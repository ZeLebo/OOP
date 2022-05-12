package ru.nsu.sartakov.task_2_2_1.entities;

public record Board(int width, int height, int cellSize) {

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCellSize() {
        return cellSize;
    }
}