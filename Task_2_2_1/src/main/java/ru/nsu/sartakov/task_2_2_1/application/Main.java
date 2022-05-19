package ru.nsu.sartakov.task_2_2_1.application;

import ru.nsu.sartakov.task_2_2_1.application.SnakeApplication;

public class Main {
    public static void main(String[] args) {
        SettingsApplication.main(args);
        SnakeApplication.main(args);
    }

    public static void restartAll() {
        main(null);
    }
}
