package ru.nsu.sartakov;

import java.lang.Math;

public class Arithmetics {
    public double add(double x, double y) {
        return x + y;
    }

    public double sub(double x, double y) {
        return x - y;
    }

    public double mult(double x, double y) {
        return x * y;
    }

    public double div(double x, double y) {
        return x / y;
    }

    public double log(double x, double y) {
        return Math.log(y) / Math.log(x);
    }

    public double pow(double x, double y) {
        return Math.pow(x, y);
    }

    public double sqrt(double x) {
        return Math.sqrt(x);
    }

    public double cos(double x) {
        return Math.cos(x);
    }

    public double sin(double x) {
        return Math.sin(x);
    }
}
