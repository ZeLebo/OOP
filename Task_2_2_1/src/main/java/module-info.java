module ru.nsu.sartakov.task_2_2_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.sartakov.task_2_2_1 to javafx.fxml;
    exports ru.nsu.sartakov.task_2_2_1;
}