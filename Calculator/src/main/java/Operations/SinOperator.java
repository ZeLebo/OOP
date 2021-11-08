package Operations;

import java.util.Stack;

public class SinOperator implements Operation{
    @Override
    public double calculate(Stack<Double> stack) {
        double x = stack.pop();
        return Math.sin(x);
    }

    @Override
    public String getRepresentation() {
        return "sin";
    }
}
