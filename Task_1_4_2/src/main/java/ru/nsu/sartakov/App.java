package ru.nsu.sartakov;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.*;

public class App {
    /**
     * @param args arguments of command line
     */
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
        options.addOption(Option.builder("test")
                .hasArg(false)
                .build());
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        if (line.hasOption("add")) {
            String[] values = line.getOptionValues("add");
            notebook.addNote(values[0], values[1]);
        } else if (line.hasOption("rm")) {
            notebook.removeNote(line.getOptionValue("rm"));
        } else if (line.hasOption("show")) {
            String[] values = line.getOptionValues("show");
            if (values == null) {
                List<Note> notes =  notebook.showAllNotes();
                for (Note i : notes) {
                    System.out.println(i.getTime());
                    System.out.println(i.getHeading());
                    System.out.println(i.getNote());
                }
            }  else if (values.length == 2) {
                List<Note> notes = notebook.showNotesPeriod(LocalDateTime.parse(values[0]), LocalDateTime.parse(values[1]));
                for (Note i : notes) {
                    System.out.println(i.getTime());
                    System.out.println(i.getHeading());
                    System.out.println(i.getNote());
                }

            } else {
                List<String> subWords = Arrays.stream(values).toList();
                subWords.remove(0);
                subWords.remove(0);
                List<Note> notes = notebook.findNotesPeriodSwords(LocalDateTime.parse(values[0]),
                                LocalDateTime.parse(values[1]),
                                subWords);

                for (Note i : notes) {
                    System.out.println(i.getTime());
                    System.out.println(i.getHeading());
                    System.out.println(i.getNote());
                }
            }
        } else {
            System.out.println("You're doing something wrong");
        }
    }
}