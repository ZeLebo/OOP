package ru.nsu.sartakov.task_2_2_1.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SnakeController {
    @FXML
    private Label restart;

    @FXML
    public void setSnakeButton() {
        restart.setText("Restart");
        SnakeApplication.main(null);
    }
}
