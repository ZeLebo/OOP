package ru.nsu.sartakov.operations;

import java.util.Stack;

public class MultOperator implements Operation {
    /**
     *
     * @param stack - the input data presented as stack
     * @return the multiplying of the last 2 numbers in stack
     */
    @Override
    public double calculate(Stack<Double> stack) {
        Double a = stack.pop();
        Double b = stack.pop();
        return a * b;
    }

    @Override
    public String getRepresentation() {
        return "*";
    }
}
