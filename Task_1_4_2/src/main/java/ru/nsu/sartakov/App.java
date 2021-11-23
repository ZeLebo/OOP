package ru.nsu.sartakov;

import java.io.IOException;
import org.apache.commons.cli.*;

public class App {
    // Todo use commons.cli parameters
    public static void main(String[] args) throws IOException {
        Notebook notebook = new Notebook();

        Options options = new Options();

        options.addOption(Option.builder("add")
                .longOpt("Addition")
                .hasArg(true)
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder("rm")
                .longOpt("Removing a note")
                .hasArg(true)
                .numberOfArgs(1)
                .build());
        options.addOption(Option.builder("show")
                .longOpt("Show the notes")
                .hasArg(true)
                .numberOfArgs(3)
                .build());

        switch (args[0]) {
            case "-add":
                try {
                    notebook.addNote(args[1], args[2]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Wrong amount of command line parameters");
                }
                break;
            case "-rm":
                try {
                    notebook.removeNote(args[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Wrong amount of command line parameters");
                }
                break;
            case "-show":
                try {
                    notebook.showAllNotes();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Too many command line argumetns");
                }
        }

        //System.out.println(notebook.showAllNotes());
    }

}