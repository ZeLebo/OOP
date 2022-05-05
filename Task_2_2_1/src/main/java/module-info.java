module ru.nsu.sartakov.snake.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


//    opens ru.nsu.sartakov.task_2_2_1 to javafx.fxml;
    exports ru.nsu.sartakov.task_2_2_1.application;
    opens ru.nsu.sartakov.task_2_2_1.application to javafx.fxml;
}