package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.order.Order;
import ru.nsu.sartakov.pizzeria.PizzeriaSettingUp;

/**
 * Entry point of the program
 */
public class App {
    /**
     * Start the pizzeria
     * @param args - arguments of the command line
     */
    public static void main(String[] args) {
        PizzeriaSettingUp app = new PizzeriaSettingUp();
        for (int i = 0; i < 10; i++) {
            app.getPizzeria().addOrder(new Order("Margarita"));
        }
        app.run();
    }
}
