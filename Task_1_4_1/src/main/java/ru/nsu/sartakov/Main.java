package ru.nsu.sartakov;

import java.util.Scanner;
public class Main {

    /**
     * input function get the string of input calculation data
     * Input must be written in prefix structure
     */
    public void main(String args) {
        Calculator calculator = new Calculator();
        Scanner inputScanner = new Scanner(System.in);
        String inputString = inputScanner.nextLine();
        System.out.println(calculator.calc(inputString));
        inputScanner.close();
    }
}
