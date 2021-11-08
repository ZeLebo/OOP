package ru.nsu.sartakov;

import Operations.*;
import java.util.ArrayList;
import java.util.List;

public class OperationFabric {
    public static List<Operation> produceOperations() {
        List<Operation> lst = new ArrayList<>();
        lst.add(new AddOperator());
        lst.add(new CosOperator());
        lst.add(new DivOperator());
        lst.add(new LogOperator());
        lst.add(new MultOperator());
        lst.add(new PowOperator());
        lst.add(new SinOperator());
        lst.add(new SqrtOperator());
        lst.add(new SubOperator());
        return lst;
    }
}
