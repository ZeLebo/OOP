package ru.nsu.sartakov.interfaces;

/**
 * The consumer interface for being implemented by class, that can receive objects
 * @param <T> - type of consumed objects
 */
public interface Consumer<T> {
    /**
     * Method to consume some object
     * @return - consumed object
     */
    T consume();
}
