package ru.nsu.sartakov.interfaces;

/**
 * The Producer interface to implement by classes that allows to add new objects to a shared resource
 * @param <T> - type of object
 */
public interface Producer<T> {
    /**
     * Method to add objects
     * @param object - type of object
     */
    void produce(T object);
}
