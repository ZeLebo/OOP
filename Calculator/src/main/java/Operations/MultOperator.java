package Operations;

import java.util.Stack;
import java.util.stream.Stream;

public class MultOperator implements Operation{
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
