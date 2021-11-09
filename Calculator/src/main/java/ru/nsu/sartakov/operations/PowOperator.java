package ru.nsu.sartakov.operations;

import java.util.EmptyStackException;
import java.util.Stack;

public class PowOperator implements Operation {
    /**
     *
     * @param stack - the input data presented as stack
     * @return the result of powering the last number for the previous number
     */
    @Override
    public double calculate(Stack<Double> stack) {
        if (stack.size() > 1) {
            double a = stack.pop();
            double b = stack.pop();
            return Math.pow(a, b);
        } else {
            throw new EmptyStackException();
        }

    }

    /**
     *
     * @return the representation of an operation
     */
    @Override
    public String getRepresentation() {
        return "pow";
    }
}
