package ru.nsu.sartakov;

import Operations.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

class CalculatorTest {

    Stack<Double> numbers;
    @BeforeEach
    public void init() {
        numbers = new Stack<>();
    }

    @Test
    public void additionTest() {
        double x = 147.72;
        double y = 243.2;
        numbers.push(y);
        numbers.push(x);
        AddOperator op = new AddOperator();
        double result = x + y;
        Assertions.assertEquals(result, op.calculate(numbers));
    }

    @Test
    public void subtractionTest() {
        double x = 1234.23;
        double y = 234432.324;
        numbers.push(y);
        numbers.push(x);
        SubOperator op = new SubOperator();
        double result = x - y;
        Assertions.assertEquals(result, op.calculate(numbers));
    }

    @Test
    public void multiplicationTest() {
        double x = 147.72;
        double y = 243.2;
        numbers.push(y);
        numbers.push(x);
        MultOperator op = new MultOperator();
        double result = x * y;
        Assertions.assertEquals(result, op.calculate(numbers));
    }

    @Test
    public void divisionTest() {
        double x = 147.72;
        double y = 243.2;
        numbers.push(y);
        numbers.push(x);
        DivOperator op = new DivOperator();
        double result = x / y;
        Assertions.assertEquals(result, op.calculate(numbers));
    }

    @Test
    public void logTest() {
        double x = 5.0;
        double y = 125.0;
        numbers.push(y);
        numbers.push(x);
        LogOperator op = new LogOperator();
        double result = 3.0;
        Assertions.assertEquals(result, op.calculate(numbers), 0.00001);
    }

    @Test
    public void powTest() {
        double x = 2.0;
        double y = 5.0;
        numbers.push(y);
        numbers.push(x);
        PowOperator op = new PowOperator();
        double result = 32.0;
        Assertions.assertEquals(result, op.calculate(numbers));
    }

    @Test
    public void sqrtTest() {
        double x = 25.0;
        numbers.push(x);
        SqrtOperator op = new SqrtOperator();
        double result = 5.0;
        Assertions.assertEquals(result, op.calculate(numbers));
    }

    @Test
    public void cosTest() {
        double x = 1.0;
        numbers.push(x);
        CosOperator op = new CosOperator();
        double result = 0.5403023059;
        Assertions.assertEquals(result, op.calculate(numbers), 0.000000001);
    }

    @Test
    public void sinTest() {
        numbers.push(1.0);
        double result = 0.8414709848;
        SinOperator op =  new SinOperator();
        Assertions.assertEquals(result, op.calculate(numbers), 0.000000001);
    }

    @Test
    public void calculationTest(){
        Calculator calculator = new Calculator();
        String input = "sin + - 1 2 1";
        double result = 0.0;
        Assertions.assertEquals(result, calculator.calc(input), 0.0001);
    }

    @Test
    public void tooManyArgumentsTest() {
        Calculator calculator = new Calculator();
        String input = "sin + - 1 2 1 1 1";
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calc(input));
    }

    @Test
    public void notEnoughArgumentsTest() {
        Calculator calculator = new Calculator();
        String input = "sin + - 1 2";
        Assertions.assertThrows(EmptyStackException.class, () -> calculator.calc(input));
    }

    @Test
    public void noArgumentsTest() {
        Calculator calculator = new Calculator();
        String input = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calc(input));
    }
}