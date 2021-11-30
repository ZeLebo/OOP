package ru.nsu.sartakov;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.*;

public class App {
    public static Notebook notebook = new Notebook();

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter printOut = new PrintWriter(System.out);
        formatter.printUsage(printOut, 100, "java -jar Task_1_4_2-1.0-SNAPSHOT.jar [options]");
        formatter.printOptions(printOut, 100, options, 2, 2);
        printOut.close();
    }

    public void mainCaller(String[] line) throws ParseException {
        main(line);
    }

    /**
     * @param args arguments of command line
     */
    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("add")
                .longOpt("Addition of a note takes 2 args <Header> <Note content>")
                .hasArg(true)
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder("rm")
                .longOpt("Removing a note takes one argument <Header>")
                .hasArg(true)
                .numberOfArgs(1)
                .build());
        options.addOption(Option.builder("show")
                .longOpt("Show the notes takes zero, two or many parameters \n<The date to search from> \n<The date to search to> \n<Subwords to find in the header>")
                .hasArg(true)
                .numberOfArgs(3)
                .optionalArg(true)
                .build());
        options.addOption(Option.builder("changeName")
                .longOpt("Change the file name to store the notes")
                .hasArg(true)
                .numberOfArgs(1)
                .build());
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        try {
            notebook.setFileName(line.getArgList().get(0));
            if (line.hasOption("add")) {
                add(line);
            } else if (line.hasOption("rm")) {
                remove(line);
            } else if (line.hasOption("show")) {
                show(line);
            } else {
                printHelp(options);
            }
        } catch (IOException e) {
            printHelp(options);
        }
    }

    public static void add(CommandLine line) throws IOException {
        String[] values = line.getOptionValues("add");
        notebook.addNote(values[0], values[1]);
    }

    public static void remove(CommandLine line) throws IOException {
        notebook.removeNote(line.getOptionValue("rm"));
    }

    public static void show(CommandLine line) throws IOException {
        String[] values = line.getOptionValues("show");
        if (values == null) {
            List<Note> notes = notebook.showAllNotes();
            if (notes.size() == 0) {
                System.out.println("You have no notes added");
            } else {
                notesPrinting(notes);
            }
        } else if (values.length == 2) {
            try {
                List<Note> notes = notebook.showNotesPeriod(LocalDateTime.parse(values[0]),
                        LocalDateTime.parse(values[1]));
                notesPrinting(notes);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Array index out of bounds show notes period on time");
            }

        } else {
            try {
                List<Note> notes = notebook.findNotesPeriodSubWords(LocalDateTime.parse(values[0]),
                        LocalDateTime.parse(values[1]),
                        Arrays.stream(values).toList());
                notesPrinting(notes);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You have no notes added to fit such params");
            }
        }
    }
    public static void notesPrinting(List<Note> notes) {
        for (Note i : notes) {
            System.out.println(i.getTime());
            System.out.println(i.getHeading());
            System.out.println(i.getNote());
        }
    }
}