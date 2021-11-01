package ru.nsu.sartakov;

import java.util.Scanner;
import java.util.Stack;

public class Calculator extends Arithmetics {

    public void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String inputString = inputScanner.nextLine();
        System.out.println(calc(inputString));
        inputScanner.close();
    }

    private boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public double calc(String inputString) {
        new Arithmetics();
        String[] input = inputString.split(" ");
        Stack <Double> stack = new Stack<>();

        for (int i = input.length - 1; i > -1 ; --i) {
            double x;
            double y;
            if (isNumeric(input[i])) {
                stack.push(Double.parseDouble(input[i]));
            } else if (input[i].equals("+")) {
                x = stack.pop();
                y = stack.pop();
                stack.push(add(x, y));
            } else if (input[i].equals("-")) {
                x = stack.pop();
                y = stack.pop();
                stack.push(sub(x, y));
            } else if (input[i].equals("*")) {
                x = stack.pop();
                y = stack.pop();
                stack.push(mult(x, y));
            } else if (input[i].equals("/")) {
                x = stack.pop();
                y = stack.pop();
                stack.push(div(x, y));
            } else if (input[i].equals("log")) {
                x = stack.pop();
                y = stack.pop();
                stack.push(log(x, y));
            } else if (input[i].equals("pow")) {
                x = stack.pop();
                y = stack.pop();
                stack.push(pow(x, y));
            } else if (input[i].equals("sqrt")) {
                x = stack.pop();
                stack.push(sqrt(x));
            } else if (input[i].equals("sin")) {
                x = stack.pop();
                stack.push(sin(x));
            } else if (input[i].equals("cos")) {
                x = stack.pop();
                stack.push(cos(x));
            } else {
                System.out.println("Something went wrong… UwU");
            }
        }
        return stack.pop();
    }
}
