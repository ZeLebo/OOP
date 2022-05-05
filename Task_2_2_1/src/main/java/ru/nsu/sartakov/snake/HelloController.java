package ru.nsu.sartakov.snake;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Label snakeGame;

    @FXML
    protected void onSnakeButtonClick() {
        snakeGame.setText("Snake Game");
    }
}