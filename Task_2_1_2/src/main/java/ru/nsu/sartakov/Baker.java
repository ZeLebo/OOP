package ru.nsu.sartakov;

import java.util.concurrent.LinkedBlockingQueue;

public class Baker implements Runnable{
    private boolean waitingOrder;
    private final int cookingTime;
    private final int id;

    Baker(BakerConfig bakerConfig) {
        this.cookingTime = bakerConfig.getCookingTime();
        this.id = bakerConfig.getId();
    }

    public boolean isWaitingOrder() {
        return waitingOrder;
    }

    public int getId() {
        return id;
    }

    public int getCockingTime() {
        return cookingTime;
    }

    @Override
    public void run() {
    // todo something has to happen here
    }
}
