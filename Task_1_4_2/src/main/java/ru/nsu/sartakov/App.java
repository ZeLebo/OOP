package ru.nsu.sartakov;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.*;

public class App {
    // Todo use commons.cli parameters
    public static void main(String[] args) throws IOException, ParseException {
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
                .hasArg()
                .optionalArg(true)
                .build());
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        if (line.hasOption("add")) {
            String[] values = line.getOptionValues("add");
            System.out.println(values[0]);
            System.out.println(values[1]);
            notebook.addNote(values[0], values[1]);
        }

        if (line.hasOption("rm")) {
            notebook.removeNote(line.getOptionValue("rm"));
        }

        if (line.hasOption("show")) {
            String[] values = line.getOptionValues("show");
            if (values == null) {
                notebook.showAllNotes();
                return;
            }  else if (values.length == 2) {
                notebook.showNotesPeriod(LocalDateTime.parse(values[0]), LocalDateTime.parse(values[1]));
            } else {
                List<String> subWords = Arrays.stream(values).toList();
                subWords.remove(0);
                subWords.remove(0);
                notebook.showNotes(LocalDateTime.parse(values[0]),
                                LocalDateTime.parse(values[1]),
                                subWords);
            }
        }

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
                    System.out.println("Too many command line arguments");
                }
        }

        //System.out.println(notebook.showAllNotes());
    }

}