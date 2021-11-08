package Operations;

import java.util.EmptyStackException;
import java.util.Stack;

public class PowOperator implements Operation{
    @Override
    public double calculate(Stack<Double> stack) {
        if (stack.size() > 1) {
            double a = stack.pop();
            double b = stack.pop();
            return Math.pow(a, b);
        } else {
            throw new EmptyStackException();
        }

    }

    @Override
    public String getRepresentation() {
        return "pow";
    }
}
