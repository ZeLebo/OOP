package Operations;

import java.util.Stack;

public class SqrtOperator implements Operation{
    @Override
    public double calculate(Stack<Double> stack) {
        double a = stack.pop();
        return Math.sqrt(a);
    }

    @Override
    public String getRepresentation() {
        return "sqrt";
    }
}
