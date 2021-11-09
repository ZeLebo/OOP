package ru.nsu.sartakov.operations;

import java.util.Stack;

public class CosOperator implements Operation{
    /**
     *
     * @param stack - the input data presented as stack
     * @return the cos of the last number in stack
     */
    @Override
    public double calculate(Stack<Double> stack) {
        double a = stack.pop();
        return Math.cos(a);
    }

    /**
     * @return the representation of an operation
     */
    @Override
    public String getRepresentation() {
        return "cos";
    }
}
