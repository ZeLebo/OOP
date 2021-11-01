package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest extends Arithmetics {

    @Test
    public void additionTest() {
        double x = 147.72;
        double y = 243.2;
        double result = x + y;
        Assertions.assertEquals(result, add(x, y));
    }

    @Test
    public void subtractionTest() {
        double x = 1234.23;
        double y = 234432.324;
        double result = x - y;
        Assertions.assertEquals(result, sub(x, y));
    }

    @Test
    public void multiplicationTest() {
        double x = 147.72;
        double y = 243.2;
        double result = x * y;
        Assertions.assertEquals(result, mult(x, y));
    }

    @Test
    public void divisionTest() {
        double x = 147.72;
        double y = 243.2;
        double result = x / y;
        Assertions.assertEquals(result, div(x, y));
    }

    @Test
    public void logTest() {
        double x = 5.0;
        double y = 125.0;
        double result = 3.0;
        Assertions.assertEquals(result, log(x, y), 0.00001);
    }

    @Test
    public void powTest() {
        double x = 2.0;
        double y = 5.0;
        double result = 32.0;
        Assertions.assertEquals(result, pow(x, y));
    }

    @Test
    public void sqrtTest() {
        double x = 25.0;
        double result = 5.0;
        Assertions.assertEquals(result, sqrt(x));
    }

    @Test
    public void cosTest() {
        double x = 1.0;
        double result = 0.5403023059;
        Assertions.assertEquals(result, cos(x), 0.000000001);
    }

    @Test
    public void sinTest() {
        double x = 1.0;
        double result = 0.8414709848;
        Assertions.assertEquals(result, sin(x), 0.000000001);
    }

    @Test
    public void calculationTest(){
        Calculator calculator = new Calculator();
        String input = "sin + - 1 2 1";
        double result = 0.0;
        Assertions.assertEquals(result, calculator.calc(input), 0.0001);
    }
}