package ru.nsu.sartakov.task_2_2_1.entities;

import javafx.scene.paint.Color;

import java.util.Random;

public class Obstacle {
    public int x;
    public int y;
    public Color color;

    private final int width;
    private final int height;

    public Obstacle(int fieldHeight, int fieldWidth) {
        this.height = fieldHeight;
        this.width = fieldWidth;
    }

    public void newObstacle() {
        Random rand = new Random();
        this.x = rand.nextInt(this.width);
        this.y = rand.nextInt(this.height);
        this.color = Color.WHITE;
    }

    public boolean isCollision(int x, int y) {
        return this.x == x && this.y == y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHit(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Obstacle obstacle)) {
            return false;
        }
        return x == obstacle.x && y == obstacle.y;
    }
}
