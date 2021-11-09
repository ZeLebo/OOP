package ru.nsu.sartakov.operations;

import java.util.Stack;

public class SqrtOperator implements Operation {
    /**
     * @param stack - the input data presented as stack
     * @return the square root of the last
     */
    @Override
    public double calculate(Stack<Double> stack) {
        double a = stack.pop();
        if (a > -1) {
            return Math.sqrt(a);
        } else {
            throw new ArithmeticException("Wrong square root");
        }

    }

    /**
     * @return the representation of an operation
     */
    @Override
    public String getRepresentation() {
        return "sqrt";
    }
}
