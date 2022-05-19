package ru.nsu.sartakov.task_2_2_1.entities;

import ru.nsu.sartakov.task_2_2_1.entities.Snake;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Food {
    private int height = 0;
    private int width = 0;
    private Snake snake;

    public int foodX = 0;
    public int foodY = 0;
    public Color foodColor = Color.WHITE;
//    public int foodColor = 0;


    public Food(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = null;
    }

    public void newFood(Snake snake) {
        this.snake = snake;

        Random rand = new Random();
        this.foodX = rand.nextInt(this.width);
        this.foodY = rand.nextInt(this.height);
        int colorNum = rand.nextInt(10);
        this.foodColor = switch (colorNum) {
            case 0 -> Color.PURPLE;
            case 1 -> Color.LIGHTBLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PINK;
            case 4 -> Color.ORANGE;
            case 5 -> Color.CHARTREUSE;
            case 6 -> Color.GRAY;
            case 7 -> Color.AQUA;
            case 8 -> Color.BEIGE;
            case 9 -> Color.BLUEVIOLET;
            default -> Color.WHITE;
        };



        while (this.snake.contains(this.foodX, this.foodY)) {
            this.foodX = rand.nextInt(this.width);
            this.foodY = rand.nextInt(this.height);
        }
    }

    public boolean isCollision(List<Obstacle> obstacleList) {
        for (Obstacle obstacle : obstacleList) {
            if (obstacle.isCollision(this.foodX, this.foodY)) {
                return true;
            }
        }
        return false;
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public Color getFoodColor() {
        return foodColor;
    }
}
