package ru.nsu.sartakov;

import ru.nsu.sartakov.operations.*;

import java.util.*;

public class OperationFabric {
    private final Map<String, Operation> operations = new HashMap<>();

    public OperationFabric() {
        List<Operation> list = OperationFabric.produceOperations();
        for (Operation op : list) {
            operations.put(op.getRepresentation(), op);
        }
    }

    public static List<Operation> produceOperations() {
        List<Operation> list = new ArrayList<>();
        list.add(new AddOperator());
        list.add(new CosOperator());
        list.add(new DivOperator());
        list.add(new LogOperator());
        list.add(new MultOperator());
        list.add(new PowOperator());
        list.add(new SinOperator());
        list.add(new SqrtOperator());
        list.add(new SubOperator());
        return list;
    }

    /**
     * @param stack where the numbers lie
     * @param input the operation
     * @return the result produced by making the opearation
     */
    public double operationCalculation(Stack <Double> stack, String input) {
        return operations.get(input).calculate(stack);
    }

    /**
     * @param symbol of the input string
     * @return whether the symbol is supported in calculator
     */
    public boolean isOperation(String symbol) {
        return operations.containsKey(symbol);
    }
}
