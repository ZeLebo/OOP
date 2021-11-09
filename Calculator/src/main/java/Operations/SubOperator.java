package Operations;

import java.util.EmptyStackException;
import java.util.Stack;

public class SubOperator implements Operation{
    /**
     * @param stack - the input data presented as stack
     * @return subtraction of the last two numbers
     */
    @Override
    public double calculate(Stack<Double> stack) {
        if (stack.size() > 1) {
            double a = stack.pop();
            double b = stack.pop();
            return a - b;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * @return the representation of an operation
     */
    @Override
    public String getRepresentation() {
        return "-";
    }
}
