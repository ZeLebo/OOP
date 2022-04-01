package ru.nsu.sartakov.pizzeria;

import ru.nsu.sartakov.json.BakerEntity;
import ru.nsu.sartakov.json.DelivererEntity;
import ru.nsu.sartakov.json.Json;
import ru.nsu.sartakov.json.PizzeriaJSON;

public class PizzeriaSettingUp implements Runnable {
    private final int workingDay = 50000;
    private PizzeriaJSON pizzeriaJSON;
    private Pizzeria pizzeria;

    /**
     * Constructor for PizzeriaSettingUp class
     */
    public PizzeriaSettingUp() {
        setPizzeriaJSON();
        setPizzeria();
    }

    /**
     * Return the link to object Pizzeria
     * @return Pizzeria
     */
    public Pizzeria getPizzeria() {
        return pizzeria;
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
        BakerEntity[] bakersJSON = pizzeriaJSON.getBakers();
        if (bakersJSON == null || bakersJSON.length == 0) {
            System.err.println("Cannot start pizzeria with such parameters [bakers are not found]");
            return;
        }
        DelivererEntity[] couriersJSON = pizzeriaJSON.getDeliverers();
        if (couriersJSON == null || couriersJSON.length == 0) {
            System.err.println("Cannot start pizzeria with such parameters [deliverer are not found]");
            return;
        }
        pizzeria = new Pizzeria(pizzeriaJSON);
    }

    /**
     * Method to start pizzeria
     * Work for workingTime, then closes
     */
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
            System.exit(0);
        } catch (InterruptedException stopped) {
            System.err.println("Pizzeria has been hacked…");
            System.exit(1);
        }
    }
}