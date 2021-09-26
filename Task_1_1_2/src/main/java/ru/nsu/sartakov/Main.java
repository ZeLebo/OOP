package ru.nsu.sartakov;

import java.util.Scanner;
import java.awt.Desktop;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // get the string
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

        if (str.isEmpty()){
            System.out.println("Enter something UwU");
            return;
        }

        String[] line = str.split(" ");
        str = "";

        for (int i = 1; i < line.length; i++) {
            str += line[i] + " ";
        }
        File file = new File (line[0]);
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            desktop.open(file);
        }

        System.out.println("The filename is " + file);
        System.out.println("The stirng to find is " + str);
    }
}
