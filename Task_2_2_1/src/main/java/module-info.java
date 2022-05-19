module ru.nsu.sartakov.snake.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires com.google.gson;
    requires org.junit.jupiter.api;


    exports ru.nsu.sartakov.task_2_2_1.application;
    opens ru.nsu.sartakov.task_2_2_1.application to javafx.fxml;
    exports ru.nsu.sartakov.task_2_2_1.entities;
    opens ru.nsu.sartakov.task_2_2_1.entities to javafx.fxml;
    exports ru.nsu.sartakov.task_2_2_1;
    opens ru.nsu.sartakov.task_2_2_1 to javafx.fxml;
}