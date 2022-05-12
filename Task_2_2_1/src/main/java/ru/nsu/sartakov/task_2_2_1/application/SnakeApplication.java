package ru.nsu.sartakov.task_2_2_1.application;

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
import ru.nsu.sartakov.task_2_2_1.entities.Board;
import ru.nsu.sartakov.task_2_2_1.entities.Cell;
import ru.nsu.sartakov.task_2_2_1.entities.Direction;
import ru.nsu.sartakov.task_2_2_1.entities.Snake;

public class SnakeApplication extends Application {
    public static int speed = 5;
    public static int foodColor = 0;
    public static int foodX = 0;
    public static int foodY = 0;

    Board board = new Board(20, 20, 25);
    Snake snake = new Snake(board.getWidth() / 2,
            board.getHeight() / 2, speed);

    static boolean gameOver = false;
    static Random rand = new Random();

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
        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0,
                board.getWidth() * board.getCellSize(),
                board.height() * board.getCellSize());


        // random food color
        Color cc = switch (foodColor) {
            case 0 -> Color.PURPLE;
            case 1 -> Color.LIGHTBLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.PINK;
            case 4 -> Color.ORANGE;
            default -> Color.WHITE;
        };

        gc.setFill(cc);
        gc.fillOval(foodX * board.getCellSize(),
                foodY * board.getCellSize(),
                board.getCellSize(),
                board.getCellSize());

        // snake
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


        newFood();

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


        primaryStage.setTitle("Snake score: " + (snake.getBody().size()));


        // update snake?
        // todo HUINYA peredelat'
        for (int i = snake.getBody().size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
        }

        snake.move();


        // eat food
        if (foodX == snake.getHead().x && foodY == snake.getHead().y) {
            snake.grow();
            snake.speedUp();
            newFood();
        }

        // is collapsed with obstacles?
        if (snake.isBumpedIntoWall(board) || snake.isBumpedIntoSnake()) {
            setGameOver();
        }
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 24));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText("GAME OVER",
                    board.getWidth() * board.getCellSize() / 2.0,
                    board.getHeight() * board.getCellSize() / 2.0);
            return;
        }


        colorizePanel(gc);

    }

    // food
    public void newFood() {
        foodX = rand.nextInt(board.getWidth());
        foodY = rand.nextInt(board.getHeight());
        // if food is on snake
        while (snake.contains(foodX, foodY)) {
            foodX = rand.nextInt(board.getWidth());
            foodY = rand.nextInt(board.getHeight());
        }
        foodColor = rand.nextInt(5);
    }

    public static void main(String[] args) {
        launch(args);
    }
}