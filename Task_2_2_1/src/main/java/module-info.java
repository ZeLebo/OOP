module ru.nsu.sartakov.snake {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.sartakov.snake to javafx.fxml;
    exports ru.nsu.sartakov.snake;
}