package ru.nsu.sartakov;

import java.io.IOException;
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

    public void addNote(String heading, String text) throws IOException {
        notes.addAll(file.readFromFile());
        notes.add(new Note(heading, text));
        file.writeToFile(notes);
    }

    public void removeNote(String heading) {
        file.writeToFile(notes.stream()
                .filter(
                        i -> ! i.getHeading().equals(heading)
                ).collect(Collectors.toList()));
    }

    public List<Note> showAllNotes() throws IOException {
        return file.readFromFile();
    }

    public List<Note> findNotesPeriodSwords(LocalDateTime from, LocalDateTime to, List<String> subWords) {
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
