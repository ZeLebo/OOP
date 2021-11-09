package ru.nsu.sartakov;

import ru.nsu.sartakov.operations.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Operation> getOperations() {
        return operations;
    }
}
