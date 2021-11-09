package ru.nsu.sartakov.operations;

import java.util.Stack;

public class SinOperator implements Operation {
    /**
     * @param stack - the input data presented as stack
     * @return the sin of the last element in the stack
     */
    @Override
    public double calculate(Stack<Double> stack) {
        double x = stack.pop();
        return Math.sin(x);
    }

    /**
     *
     * @return the representation of an operation
     */
    @Override
    public String getRepresentation() {
        return "sin";
    }
}
