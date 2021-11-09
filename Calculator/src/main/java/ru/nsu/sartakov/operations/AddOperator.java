package ru.nsu.sartakov.operations;

import java.util.EmptyStackException;
import java.util.Stack;

public class AddOperator implements Operation{
    /**
     *
     * @param stack - the input data presented as stack
     * @return the sum of the last two elements in stack
     */
    @Override
    public double calculate(Stack<Double> stack) {
        if (stack.size() > 1 ) {
            double a = stack.pop();
            double b = stack.pop();
            return a + b;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * @return the operation symbol
     */
    @Override
    public String getRepresentation() {
        return "+";
    }
}
