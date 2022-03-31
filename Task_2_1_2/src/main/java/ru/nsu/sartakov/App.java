package ru.nsu.sartakov;

import ru.nsu.sartakov.order.Order;
import ru.nsu.sartakov.pizzeria.PizzeriaSettingUp;

public class App {
    public static void main(String[] args) {
        PizzeriaSettingUp app = new PizzeriaSettingUp();
        app.addOrder(new Order("Margarita"));
        app.addOrder(new Order("Margarita"));
        app.addOrder(new Order("Margarita"));
        app.run();
    }
}
