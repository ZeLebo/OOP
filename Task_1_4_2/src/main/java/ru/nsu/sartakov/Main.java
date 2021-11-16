package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.List;

public class Main {
    List<Notebook> noteList = new ArrayList();
    public static void main(String[] args) {
        for (String i : args) {
            if (i.equals("-d")) {
                System.out.println("got it");
            } else if (i.equals("d")) {
                System.out.println("d");
            } else {
                System.out.println(i);
            }
        }
        System.out.println("Hello world");
        System.out.println("Hello");
    }
}
