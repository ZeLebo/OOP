package ru.nsu.sartakov.snake.entities;

public class Board {
    private int width;
    private int height;
    private int cellSize;

    public Board(int width, int height, int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
    }

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
