package ru.nsu.sartakov.task_2_2_1.entities;

import ru.nsu.sartakov.task_2_2_1.entities.Snake;

import java.util.Random;



public class Food {
    private int height = 0;
    private int width = 0;
    private Snake snake;

    public int foodX = 0;
    public int foodY = 0;
    public int foodColor = 0;


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
        this.foodColor = rand.nextInt(5);

        while (this.snake.contains(this.foodX, this.foodY)) {
            this.foodX = rand.nextInt(this.width);
            this.foodY = rand.nextInt(this.height);
        }
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public int getFoodColor() {
        return foodColor;
    }

    public boolean isFoodEaten(Snake snake){
        return snake.contains(this.foodX, this.foodY);
    }
}
