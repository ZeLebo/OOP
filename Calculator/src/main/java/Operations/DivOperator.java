package Operations;

import java.util.EmptyStackException;
import java.util.Stack;

public class DivOperator implements Operation{
    /**
     *
     * @param stack - the input data presented as stack
     * @return the result of the last two numbers division
     */
    @Override
    public double calculate(Stack<Double> stack) {
        if (stack.size() > 1) {
            double a = stack.pop();
            double b = stack.pop();
            return a / b;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     *
     * @return the representation of an operation
     */
    @Override
    public String getRepresentation() {
        return "/";
    }
}
