package ru.nsu.sartakov;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Objects;

public class App {
    public static void main(String[] args) throws IOException {
        Notebook notebook = new Notebook();

        switch (args[0]) {
            case "-add":
                try {
                    System.out.println(args[0]);
                    System.out.println(args[1]);
                    System.out.println(args[2]);
                    notebook.add(args[1], args[2]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Wrong amount of command line parameters");
                }
                break;
            case "-rm":
                try {
                    notebook.rm(args[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Wrong amount of command line parameters");
                }
                break;
        }
    }

}