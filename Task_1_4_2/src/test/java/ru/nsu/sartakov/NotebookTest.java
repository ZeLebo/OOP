package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class NotebookTest {
    Notebook notebook = new Notebook();
    @BeforeEach
    public void init() throws IOException {
        notebook.removeNote("Header");
    }

    @Test
    public void addTest() throws IOException {
        notebook.addNote("Header", "The new note content");
        Note note = new Note("Header", "The new note content");
        Assertions.assertEquals(note.getTime().withNano(0), notebook.showAllNotes().get(0).getTime().withNano(0));
        Assertions.assertEquals(note.getHeading(), notebook.showAllNotes().get(0).getHeading());
        Assertions.assertEquals(note.getNote(), notebook.showAllNotes().get(0).getNote());
    }

    @Test
    public void removeTest() throws IOException {
        notebook.addNote("Stay", "with me");
        Note note = new Note("Stay", "with me");
        notebook.addNote("Header", "The new note content");
        notebook.removeNote("Header");
        Assertions.assertEquals(note.getTime().withNano(0), notebook.showAllNotes().get(0).getTime().withNano(0));
        Assertions.assertEquals(note.getHeading(), notebook.showAllNotes().get(0).getHeading());
        Assertions.assertEquals(note.getNote(), notebook.showAllNotes().get(0).getNote());
    }

    @Test
    public void showAllNotes() throws IOException {
        List<Note> notes = new ArrayList<>();
        notebook.addNote("Header", "Hello");
        notes.add(new Note("Header" ,"Hello"));
        notebook.addNote("Header", "Hello");
        notes.add(new Note("Header" ,"Hello"));
        notebook.addNote("Header", "Hello");
        notes.add(new Note("Header" ,"Hello"));
        for (int i = 0; i < notes.size(); i++) {
            Assertions.assertEquals(notes.get(i).getTime().withNano(0), notebook.showAllNotes().get(i).getTime().withNano(0));
            Assertions.assertEquals(notes.get(i).getHeading(), notebook.showAllNotes().get(i).getHeading());
            Assertions.assertEquals(notes.get(i).getNote(), notebook.showAllNotes().get(i).getNote());
        }
    }

    @Test
    public void showNotesPeriod() throws IOException, InterruptedException {
        notebook.addNote("Header", "Hello");
        notebook.addNote("Not important", "");
        LocalDateTime timeToTest = LocalDateTime.now();
        Thread.sleep(5000);
        Note note = new Note("ZhoRa", "This is the note, I wanna see");
        notebook.addNote("ZhoRa", "This is the note, I wanna see");
        Note result = notebook.showNotesPeriod(timeToTest,
                LocalDateTime.parse("2022-11-25T18:12:40")).get(0);

        Assertions.assertEquals(result.getNote(), note.getNote());

    }

    @Test
    public void findNotesPeriodSubwords() throws IOException {
        List<String> subwords = new ArrayList<>();
        subwords.add("ZhoRa");
        notebook.addNote("Header", "Hello");
        notebook.addNote("Not important", "");
        notebook.addNote("ZhoRa", "This is the note, I wanna see");
        Note note = new Note("ZhoRa", "This is the note, I wanna see");
        Note result = notebook.findNotesPeriodSwords(LocalDateTime.parse("2020-11-25T18:12:40"),
                LocalDateTime.parse("2022-11-25T18:12:40"), subwords).get(0);
        Assertions.assertEquals(result.getNote(), note.getNote());
    }
}