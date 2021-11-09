package ru.nsu.sartakov.operations;

import java.util.Stack;

public interface Operation {
    /**
     * @param stack - the input data presented as stack
     */
    double calculate(Stack<Double> stack);

    /**
     * @return string representation of an operation
     */
    String getRepresentation();
}
