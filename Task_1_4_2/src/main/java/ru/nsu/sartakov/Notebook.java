package ru.nsu.sartakov;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook  {
    Json file = new Json();

    private final List<Note> notes;

    public Notebook() {
        notes = new ArrayList<>();
    }


// TODO put the note in the end of file
    public void addNote(String heading, String text) {
        Note newNote = new Note(heading, text);
        notes.add(newNote);
        file.writeToFile(newNote);
    }
// TODO get the file and read it
    public void removeNote(String heading) {
        notes.stream()
                .filter(
                        i -> ! i.getHeading().equals(heading)
                );
    }

    public List<Note> showAllNotes() throws IOException {
        return file.readFromFile();
    }

    public List<Note> showNotes(LocalDateTime from, LocalDateTime to, List<String> subWords) {
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

    public List<Note> showNotesPeriod(LocalDateTime from, LocalDateTime to) {
        return notes.stream()
                .filter(i -> i.getTime().isAfter(from))
                .filter(i -> i.getTime().isBefore(to))
                .collect(Collectors.toList());
    }
}
