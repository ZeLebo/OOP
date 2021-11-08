package Operations;

import java.util.Stack;

public interface Operation {
    double calculate(Stack<Double> stack);
    String getRepresentation();
}
