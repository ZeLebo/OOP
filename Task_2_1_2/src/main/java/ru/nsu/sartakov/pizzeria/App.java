package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.order.Order;
import ru.nsu.sartakov.pizzeria.PizzeriaSettingUp;

public class App {
    public static void main(String[] args) {
        PizzeriaSettingUp app = new PizzeriaSettingUp();
        for (int i = 0; i < 10; i++) {
            app.getPizzeria().addOrder(new Order("Margarita"));
        }
        app.run();
    }
}
