package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.json.BakerJSON;
import ru.nsu.sartakov.json.DelivererJSON;
import ru.nsu.sartakov.json.Json;
import ru.nsu.sartakov.json.PizzeriaJSON;
import ru.nsu.sartakov.order.Order;

public class PizzeriaSettingUp implements Runnable {
    private final int workingDay = 200;
    private PizzeriaJSON pizzeriaJSON;
    private Pizzeria pizzeria;

    public PizzeriaSettingUp() {
        setPizzeriaJSON();
        setPizzeria();
    }

    private void setPizzeriaJSON() {
        Json json = new Json();
        json.open();
        this.pizzeriaJSON = json.read();
        json.close();
    }

    private void setPizzeria() {
        if (pizzeriaJSON == null) {
            System.err.println("No way to configure pizzeria");
            return;
        }
        if (pizzeriaJSON.getQueue() <= 0) {
            System.err.println("Cannot start pizzeria with such parameters [no queue size provided]");
            return;
        }
        if (pizzeriaJSON.getCapacity() <= 0) {
            System.err.println("Cannot start pizzeria with such parameters [no capacity provided]");
            return;
        }
        BakerJSON[] bakersJSON = pizzeriaJSON.getBakers();
        if (bakersJSON == null || bakersJSON.length == 0) {
            System.err.println("Cannot start pizzeria with such parameters (bakers are not found)");
            return;
        }
        DelivererJSON[] couriersJSON = pizzeriaJSON.getDeliverers();
        if (couriersJSON == null || couriersJSON.length == 0) {
            System.err.println("Cannot start pizzeria with such parameters (deliverer are not found)");
            return;
        }
        pizzeria = new Pizzeria(pizzeriaJSON);
    }

    public void addOrder(Order order) {
        this.pizzeria.addOrder(order);
    }

    @Override
    public void run() {
        if (pizzeria == null) {
            System.err.println("Pizzeria doesn't exist");
            System.exit(-1);
        }
        try {
            Thread pizzeriaThread = new Thread(pizzeria);
            pizzeriaThread.start();
            Thread.sleep(this.workingDay);
            pizzeria.stop();
        } catch (InterruptedException stopped) {
            System.err.println("Pizzeria has been hacked…");
            System.exit(1);
        }
    }
}