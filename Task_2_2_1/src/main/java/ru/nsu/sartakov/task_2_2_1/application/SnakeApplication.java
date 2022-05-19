package ru.nsu.sartakov.task_2_2_1.application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import ru.nsu.sartakov.task_2_2_1.entities.*;
import ru.nsu.sartakov.task_2_2_1.settings.SettingsLoader;

public class SnakeApplication extends Application {
    static boolean gameOver = false;
    static boolean gameWin = false;
    static boolean isPaused = false;

    public int winningScore = 10;
    public int score = 0;
    public int snakeSpeed = 5;

    public int foodAmount = 10;
    public int obstacleAmount = 5;

    public int width = 50;
    public int height = 50;
    public int cellSize = 25;

    public SnakeApplication() {}

    Board board = new Board(width, height, cellSize);
    Snake snake = new Snake(board.getWidth() / 2,
            board.getHeight() / 2, snakeSpeed);


    public ArrayList<Food> foodList = new ArrayList<>(foodAmount);
    public ArrayList<Obstacle> obstacleList = new ArrayList<>(obstacleAmount);


    public void setGameWin() {
        gameWin = true;
    }

    public void gameOver() {
        gameOver = true;
    }

    public void changePauseState() {
        isPaused = !isPaused;
    }

    // generate obstacles
    private void generateObstacles() {
        for (int i = 0; i < obstacleAmount; i++) {
            obstacleList.add(new Obstacle(board.height(), board.width()));
        }

        for (Obstacle obstacle : obstacleList) {
            obstacle.newObstacle();
        }
    }

    // generate food
    private void generateFood() {
        for (int i = 0; i < foodAmount; i++) {
            foodList.add(new Food(board.getWidth(), board.getHeight()));
        }
        // generate food on the board with no collisions with obstacles
        for (Food food : foodList) {
            food.newFood(snake);
            while (food.isCollision(obstacleList)) {
                food.newFood(snake);
            }
        }
    }

    // restart the game
    public void restart() {
        snake = new Snake(board.getWidth() / 2, board.getHeight() / 2, snakeSpeed);
        foodList.clear();
        obstacleList.clear();
        generateFood();
        generateObstacles();
        score = 0;
        isPaused = false;
        gameOver = false;
        gameWin = false;
    }

    //todo make settings screen
    @Override
    public void start(Stage primaryStage) {
        // load settings
        loadSettings();

        Canvas canvas = new Canvas(board.getWidth() * board.getCellSize(),
                board.getHeight() * board.getCellSize());
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        VBox root = new VBox();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root,
                board.getWidth() * board.getCellSize(),
                board.getHeight() * board.getCellSize());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.show();

        // make obstacles
        generateObstacles();

        // make food
        generateFood();

        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    frame(graphicsContext, primaryStage);
                    return;
                }

                if (now - lastTick > 1000000000 / snake.getSpeed()) {
                    lastTick = now;
                    frame(graphicsContext, primaryStage);
                }
            }
        }.start();

        keyListener(scene);
    }

    // tick
    public void frame(GraphicsContext gc, Stage primaryStage) {
        primaryStage.setTitle("Snake score: " + score);
        // check if the game is paused
        if (isPaused) {
            return;
        }
        // check if winning score is reached
        if (snake.getBody().size() >= winningScore) {
            setGameWin();
        }
        snake.move(); // here's supposed to be the snake moving

        // eating food
        for (Food f : foodList) {
            if (snake.getHead().x == f.foodX && snake.getHead().y == f.foodY) {
                snake.grow();
                snake.speedUp();
                score++;
                // check if possible to place a new food
                if (width * height - snake.getBody().size() - foodList.size() - obstacleList.size() <= 0) {
                    setGameWin();
                } else {
                    f.newFood(snake);
                }
            }
        }

        // check if the game is over
        checkGameOver();

        if (gameWin) {
            showScene(gc, Color.GREEN, "You win!");
            return;
        }
        if (gameOver) {
            showScene(gc, Color.RED, "GAME OVER\nPress R to restart");
            return;
        }

        colorizePanel(gc);

    }

    // check if the game is over
    private void checkGameOver() {
        // is collapsed with walls or itself
        if (snake.isBumpedIntoWall(board) || snake.isBumpedIntoSnake()) {
            gameOver();
        }

        // is collided with obstacles
        for (Obstacle obstacle : obstacleList) {
            if (snake.getHead().x == obstacle.getX() && snake.getHead().y == obstacle.getY()) {
                gameOver();
            }
        }
    }

    private void showScene(GraphicsContext gc, Color color, String s) {
        gc.setFill(color);
        gc.setFont(new Font("", 24));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(s,
                board.getWidth() * board.getCellSize() / 2.0,
                board.getHeight() * board.getCellSize() / 2.0);
    }

    private void colorizePanel(GraphicsContext gc) {
        // draw background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0,
                board.getWidth() * board.getCellSize(),
                board.height() * board.getCellSize());

        // draw borders of the board as line of 1 pixel
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRect(0, 0,
                board.getWidth() * board.getCellSize(),
                board.height() * board.getCellSize());

        // draw grid
        gc.setStroke(Color.DARKGRAY);
        for (int i = 0; i < board.getWidth(); i++) {
            gc.strokeLine(i * board.getCellSize(), 0,
                    i * board.getCellSize(),
                    board.height() * board.getCellSize());
        }
        for (int i = 0; i < board.getHeight(); i++) {
            gc.strokeLine(0, i * board.getCellSize(),
                    board.getWidth() * board.getCellSize(),
                    i * board.getCellSize());
        }

        // draw obstacles
        for (Obstacle obstacle : obstacleList) {
            gc.setFill(obstacle.color);
            gc.fillRect(obstacle.getX() * board.getCellSize(),
                    obstacle.getY() * board.getCellSize(),
                    board.getCellSize(), board.getCellSize());
        }

        // draw the food
        for (Food food : foodList) {
            gc.setFill(food.getFoodColor());
            gc.fillOval(food.foodX * board.getCellSize(),
                    food.foodY * board.getCellSize(),
                    board.getCellSize(), board.getCellSize());
        }

        // draw the snake
        for (Cell c : snake.getBody()) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * board.getCellSize(),  c.y * board.getCellSize(),
                    board.getCellSize() - 1, board.getCellSize() - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * board.getCellSize(), c.y * board.getCellSize(),
                    board.getCellSize()- 2,  board.cellSize() - 2);

        }
    }

    private void keyListener(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
                if (snake.getDirection() == Direction.DOWN) return;
                snake.setDirection(Direction.UP);
            }
            if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
                if (snake.getDirection() == Direction.RIGHT) return;
                snake.setDirection(Direction.LEFT);
            }
            if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
                if (snake.getDirection() == Direction.UP) return;
                snake.setDirection(Direction.DOWN);
            }
            if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
                if (snake.getDirection() == Direction.LEFT) return;
                snake.setDirection(Direction.RIGHT);
            }
            // if shift is pressed, the snake will grow
            if (key.getCode() == KeyCode.SHIFT) {
                snake.shift();
            }
            if (key.getCode() == KeyCode.SPACE) {
                changePauseState();
            }
            if (key.getCode() == KeyCode.ESCAPE) {
                saveSettings();
                SettingsApplication.startSnake();
                System.exit(0);
            }
            if (key.getCode() == KeyCode.R) {
                //restart
                restart();
            }
        });
    }


    // load class settings from json file
    private void loadSettings() {
        SettingsLoader settings = new SettingsLoader();
        try {
            settings.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.winningScore = settings.winningScore;
        this.snakeSpeed = settings.snakeSpeed;
        this.foodAmount = settings.foodAmount;
        this.obstacleAmount = settings.obstacleAmount;
        this.width = settings.width;
        this.height = settings.height;
        this.cellSize = settings.cellSize;
    }

    // save class settings to json file
    private void saveSettings() {
        SettingsLoader settings = new SettingsLoader(this.winningScore, this.snakeSpeed, this.foodAmount,
                        this.obstacleAmount, this.width, this.height, this.cellSize);
        try {
            settings.write();
        } catch (Exception e) {
            System.out.println("Error saving settings");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}