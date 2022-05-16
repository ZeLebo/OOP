package ru.nsu.sartakov.task_2_2_1.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class SnakeApplication extends Application {
    public static int winningScore = 10;
    public static int snakeSpeed = 5;
    public static int foodAmount = 10;
    public static int width = 40;
    public static int height = 40;
    public static int cellSize = 25;

    Board board = new Board(width, height, cellSize);
    Snake snake = new Snake(board.getWidth() / 2,
            board.getHeight() / 2, snakeSpeed);


    public ArrayList<Food> foodList = new ArrayList<>(foodAmount);


    static boolean gameOver = false;
    static boolean gameWin = false;

    public void setGameWin() {
        gameWin = true;
    }

    public void setGameOver() {
        gameOver = true;
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
        });
    }

    private void colorizePanel(GraphicsContext gc) {
        // draw background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0,
                board.getWidth() * board.getCellSize(),
                board.height() * board.getCellSize());


        // random food color
        for (Food food : foodList) {
            Color cc = switch (food.foodColor) {
                case 0 -> Color.PURPLE;
                case 1 -> Color.LIGHTBLUE;
                case 2 -> Color.YELLOW;
                case 3 -> Color.PINK;
                case 4 -> Color.ORANGE;
                default -> Color.WHITE;
            };

            // draw the food
            gc.setFill(cc);
            gc.fillOval(food.foodX * board.getCellSize(),
                    food.foodY * board.getCellSize(),
                    board.getCellSize(),
                    board.getCellSize());
        }

        // draw the snake
        for (Cell c : snake.getBody()) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * board.getCellSize(),
                    c.y * board.getCellSize(),
                    board.getCellSize() - 1,
                    board.getCellSize() - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * board.getCellSize(),
                    c.y * board.getCellSize(),
                    board.getCellSize()- 2,
                    board.cellSize() - 2);

        }
    }


    @Override
    public void start(Stage primaryStage) {
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

        for (int i = 0; i < foodAmount; i++) {
            foodList.add(new Food(board.getWidth(), board.getHeight()));
        }
        for (Food food : foodList) {
            food.newFood(snake);
        }

        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(graphicsContext, primaryStage);
                    return;
                }

                if (now - lastTick > 1000000000 / snake.getSpeed()) {
                    lastTick = now;
                    tick(graphicsContext, primaryStage);
                }
            }
        }.start();

        keyListener(scene);
    }

    // tick
    public void tick(GraphicsContext gc, Stage primaryStage) {
        // check if winning score is reached
        if (snake.getBody().size() >= winningScore) {
            setGameWin();
        }

        primaryStage.setTitle("Snake score: " + (snake.getBody().size()));

        // update snake?
        // todo HUINYA peredelat'
        for (int i = snake.getBody().size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
        }

        snake.move(); // here's supposed to be the snake moving

        // eat food
        for (Food f : foodList) {
            if (snake.getHead().x == f.foodX && snake.getHead().y == f.foodY) {
                snake.grow();
                snake.speedUp();
                f.newFood(snake);
            }
        }

        // is collapsed with obstacles?
        if (snake.isBumpedIntoWall(board) || snake.isBumpedIntoSnake()) {
            setGameOver();
        }

        if (gameWin) {
            gc.setFill(Color.GREEN);
            gc.setFont(new Font("", 24));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText("You win!",
                    board.getWidth() * board.getCellSize() / 2.0,
                    board.getHeight() * board.getCellSize() / 2.0);
            // todo: restart
            return;
        }
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 24));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText("GAME OVER",
                    board.getWidth() * board.getCellSize() / 2.0,
                    board.getHeight() * board.getCellSize() / 2.0);
            // todo: restart
            return;
        }

        colorizePanel(gc);

    }

    public static void main(String[] args) {
        launch(args);
    }
}