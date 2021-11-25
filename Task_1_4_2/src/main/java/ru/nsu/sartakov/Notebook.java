package ru.nsu.sartakov;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook  {
    private final Json file = new Json();
    private List<Note> notes;
    public Notebook() {
        notes = new ArrayList<>();
    }

    /**
     * Adding a note to a file
     * @param heading of the note
     * @param text of the note
     * @throws IOException if the file wasn't found
     */
    public void addNote(String heading, String text) throws IOException {
        try {
            notes.addAll(file.readFromFile());
            notes.add(new Note(heading, text));
            file.writeToFile(notes);
        } catch (FileNotFoundException fe) {
            System.out.println("The file wasn't found");
        }
    }

    /**
     * Removing a notes with such heading
     * @param heading of the note
     */
    public void removeNote(String heading) throws IOException {
        try {
            file.writeToFile(notes.stream()
                    .filter(
                            i -> !i.getHeading().equals(heading)
                    ).collect(Collectors.toList()));
        } catch (FileNotFoundException ex) {
            System.out.println("The file wasn't found");
        }
    }

    /**
     * @return the list of all notes from the file
     * @throws IOException if the file doesn't exist
     */
    public List<Note> showAllNotes() throws IOException {
        return file.readFromFile();
    }

    /**
     * @param from the start of time period
     * @param to the end of time period
     * @return a list of note that were made in time period
     */
    public List<Note> showNotesPeriod(LocalDateTime from, LocalDateTime to) throws IOException {
        notes = file.readFromFile();
        return notes.stream()
                .filter(i -> i.getTime().withNano(0).isAfter(from))
                .filter(i -> i.getTime().withNano(0).isBefore(to))
                .collect(Collectors.toList());
    }

    /**
     * @param from the start period of time
     * @param to the end period of time
     * @param subWords of the heading
     * @return list of notes that fit the params
     */
    public List<Note> findNotesPeriodSwords(LocalDateTime from, LocalDateTime to, List<String> subWords) throws IOException {
        notes = file.readFromFile();
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