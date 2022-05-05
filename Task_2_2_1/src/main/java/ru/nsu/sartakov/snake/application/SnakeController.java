package ru.nsu.sartakov.snake.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SnakeController {
    @FXML
    private Label snakeButton;

    public void setSnakeButton() {
        snakeButton.setText("Snake");
    }
}
