package ru.nsu.sartakov.json;

import ru.nsu.sartakov.collections.BakerCollection;
import ru.nsu.sartakov.collections.DeliveryCollection;

public class Storage {
    public static BakerCollection bakers() {
        return new JsonBaker();
    }

    public static DeliveryCollection deliverers() {
        return new JsonDeliverer();
    }
}
