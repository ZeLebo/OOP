package Operations;

import java.util.EmptyStackException;
import java.util.Stack;

public class LogOperator implements Operation{
    @Override
    public double calculate(Stack<Double> stack) {
        if (stack.size() > 1) {
            double a = stack.pop();
            double b = stack.pop();
            if ( a != 1 && a > 0) {
                return Math.log(b) / Math.log(a);
            } else {
                throw new ArithmeticException("You can't provide such base");
            }
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public String getRepresentation() {
        return "log";
    }
}