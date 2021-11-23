package ru.nsu.sartakov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
        options.addOption(Option.builder("test")
                .hasArg(false)
                .build());
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        if (line.hasOption("test")) {
            System.out.println("Testing this out");

            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("Notes.json"));

            List<Note> note = new Gson().fromJson(reader,new TypeToken<List<Note>>(){}.getType());
            reader.close();

            System.out.println(note);
        }

        if (line.hasOption("add")) {
            String[] values = line.getOptionValues("add");
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
    }
}