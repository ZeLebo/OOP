package ru.nsu.sartakov.task_2_2_1.application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.nsu.sartakov.task_2_2_1.settings.SettingsLoader;

public class SettingsApplication {
    public static void settingsMenu(Stage theStage, SnakeApplication snakeApp) {
        SettingsLoader settings = new SettingsLoader();
        try {
            settings.loadSettings();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage newWindow = new Stage();

        Label winLabel = new Label("Score to win");
        TextField winTextField = new TextField();
        winTextField.setText(String.valueOf(settings.winningScore));

        Label foodLabel = new Label("Food at the same time");
        Slider foodSlider = new Slider(1, 15, 10);
        foodSlider.setShowTickMarks(true);
        foodSlider.setShowTickLabels(true);
        foodSlider.setBlockIncrement(1);
        foodSlider.setMajorTickUnit(1);
        foodSlider.setMinorTickCount(0);
        foodSlider.setSnapToTicks(true);


        Label snakeSpeedLabel = new Label("Start snake speed");
        TextField snakeTextField = new TextField();
        snakeTextField.setText(String.valueOf(settings.snakeSpeed));

        Label obstacleLabel = new Label("Obstacle on the field at the same time");
        TextField obstacleTextField = new TextField();
        obstacleTextField.setText(String.valueOf(settings.obstacleAmount));

        Label widthLabel = new Label("Width of the field");
        TextField widthTextField = new TextField();
        widthTextField.setText(String.valueOf(settings.width));


        Label heightLabel = new Label("Height of the field");
        TextField heightTextField = new TextField();
        heightTextField.setText(String.valueOf(settings.height));

        Button saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setOnAction(event2 -> {
            int winScore = Integer.parseInt(winTextField.getText());
            int foodCount = (int) foodSlider.getValue();
            int snakeSpeed = Integer.parseInt(snakeTextField.getText());
            int obstacleCount = Integer.parseInt(obstacleTextField.getText());
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());

            SettingsLoader settingsLoader = new SettingsLoader(
                    winScore, snakeSpeed, foodCount,
                    obstacleCount, width, height, 25);
            try {
                settingsLoader.saveSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }

            newWindow.close();
            snakeApp.restart();
        });

        VBox vboxSettings = new VBox();
        vboxSettings.setAlignment(Pos.CENTER);
        vboxSettings.setSpacing(10);
        vboxSettings.getChildren().addAll(winLabel, winTextField,
                foodLabel, foodSlider,
                obstacleLabel, obstacleTextField,
                snakeSpeedLabel, snakeTextField,
                widthLabel, widthTextField,
                heightLabel, heightTextField,
                saveButton);
        vboxSettings.setSpacing(10);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(vboxSettings);
        Scene secondScene = new Scene(secondaryLayout);

        // New window (Stage)
        newWindow.setTitle("Settings");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(theStage);
        newWindow.setX(theStage.getX());
        newWindow.setY(theStage.getY());
        newWindow.show();
    }

    public static void startSnake() {
        SnakeApplication.main(null);
    }
}
