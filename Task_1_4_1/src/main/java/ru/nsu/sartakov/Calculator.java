package ru.nsu.sartakov;

import ru.nsu.sartakov.operations.*;

import java.util.*;

public class Calculator {
    private boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param inputString the calculation expression in prefix
     * @return the result of expression or Exception
     */
    public double calc(String inputString) {
        OperationFabric fabric = new OperationFabric();
        String[] input = inputString.split(" ");
        Stack <Double> stack = new Stack<>();

        for (int i = input.length - 1; i > -1 ; --i) {
            if (isNumeric(input[i])) {
                stack.push(Double.parseDouble(input[i]));
            } else if (fabric.isOperation(input[i])) {
                stack.push(fabric.operationCalculation(stack, input[i]));
            } else {
                throw new IllegalArgumentException("The operation wasn't found");
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("The amount of arguments is wrong");
        }
        return stack.pop();
    }
}
