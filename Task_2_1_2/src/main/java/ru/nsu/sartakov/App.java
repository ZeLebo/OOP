package ru.nsu.sartakov;

import ru.nsu.sartakov.entities.BakerEntity;
import ru.nsu.sartakov.entities.DeliveryEntity;
import ru.nsu.sartakov.json.Storage;
import ru.nsu.sartakov.pizzeria.Pizzeria;

import java.util.List;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria(10, Logger.getLogger("Pizzeria Logger"));

        List<BakerEntity> bakers = Storage;
        List<DeliveryEntity> deliverers = Storage.deliverers().findAll();

        for (var baker : bakers) {
            pizzeria.addBaker(baker);
        }

        for (var deliver : deliverers) {
            pizzeria.addDeliver(deliver);
        }

        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
        pizzeria.addOrder("Margarita");
    }
}
