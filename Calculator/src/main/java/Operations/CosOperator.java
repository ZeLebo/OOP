package Operations;

import java.util.Stack;

public class CosOperator implements Operation{
    @Override
    public double calculate(Stack<Double> stack) {
        double a = stack.pop();
        return Math.cos(a);
    }

    @Override
    public String getRepresentation() {
        return "cos";
    }
}
