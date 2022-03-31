package ru.nsu.sartakov.pizzeria;

import jdk.jshell.spi.ExecutionControl;
import ru.nsu.sartakov.json.BakerJSON;
import ru.nsu.sartakov.json.DelivererJSON;
import ru.nsu.sartakov.json.Json;
import ru.nsu.sartakov.json.PizzeriaJSON;
import ru.nsu.sartakov.order.Order;

import java.io.IOException;

public class PizzeriaSettingUp implements Runnable {
    private final int workingDay = 200;
    private PizzeriaJSON pizzeriaJSON;
    private Pizzeria pizzeria;

    public PizzeriaSettingUp() {
        try {
            setPizzeria();
        } catch (IOException e) {
            System.err.println(e);
        }
        setPizzeriaJSON();
    }

    private void setPizzeriaJSON() {
        Json json = new Json();
        json.open();
        json.read();
        json.close();
    }

    private void setPizzeria() throws IOException {
        if (pizzeriaJSON == null) {
            throw new IOException("No way to configure pizzeria");
        }
        if (pizzeriaJSON.getQueueSize() <= 0) {
            throw new IOException("Cannot start pizzeria with such parameters");
        }
        if (pizzeriaJSON.getStorageCapacity() <= 0) {
            throw new IOException("Cannot start pizzeria with such parameters");
        }
        BakerJSON[] bakersJSON = pizzeriaJSON.getBakers();
        if (bakersJSON == null || bakersJSON.length == 0) {
            throw new IOException("Cannot start pizzeria with such parameters (bakers are not found)");
        }
        DelivererJSON[] couriersJSON = pizzeriaJSON.getDeliverers();
        if (couriersJSON == null || couriersJSON.length == 0) {
            throw new IOException("Cannot start pizzeria with such parameters (deliverer are not found)");
        }
        pizzeria = new Pizzeria(pizzeriaJSON);
    }

    @Override
    public void run() {
        if (pizzeria == null) {
            System.err.println("Cannot start pizzeria");
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