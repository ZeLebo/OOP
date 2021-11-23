package ru.nsu.sartakov;

import com.google.gson.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//todo get rid of the file writting in this class, only making notes, then parsing

public class Notebook  {
    Json file = new Json();

    private final List<Note> notes;

    public Notebook() {
        notes = new ArrayList<>();
    }

    public void add(String heading, String text) throws IOException {
        Note newNote = new Note(heading, text);
        notes.add(newNote);
        file.understand(newNote);
    }

    public void rm(String heading) {
        notes.stream()
                .filter(
                        i -> ! i.getHeading().equals(heading)
                );
    }

    public List<Note> show(LocalDateTime from, LocalDateTime to, List<String> subWords) {
        return notes.stream()
                .filter(i -> i.getTime().isAfter(from))
                .filter(i -> i.getTime().isBefore(to))
                .filter(j -> subWords.stream()
                        .anyMatch(
                                i -> j.getHeading()
                                        .contains(i)
                        )
                )
                .collect(Collectors.toList());
    }
}
