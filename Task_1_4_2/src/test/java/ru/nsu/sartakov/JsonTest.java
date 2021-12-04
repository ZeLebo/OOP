package ru.nsu.sartakov;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {
    Notebook notebook = new Notebook();
    Json gson = new Json("Notes.json");

    @Test
    public void readFromFile() throws IOException {
        List<Note> notes = new ArrayList<>();
        Note note = new Note("Header", "Content");
        notebook.addNote("Header", "Content");
        notes.add(note);
        note = new Note("New header", "New content");
        notebook.addNote("New header", "New content");
        notes.add(note);
        for (int i = 0; i < notes.size(); i++) {
            Assertions.assertEquals(notes.get(i).getTime().withNano(0), gson.readFromFile().get(i).getTime().withNano(0));
            Assertions.assertEquals(notes.get(i).getHeading(), gson.readFromFile().get(i).getHeading());
            Assertions.assertEquals(notes.get(i).getNote(), gson.readFromFile().get(i).getNote());
        }
    }

    @Test
    public void writeToFile() throws IOException {
        List<Note> notes = new ArrayList<>();
        Note note = new Note("Header", "Content");
        notes.add(note);
        note = new Note("New header", "New content");
        notes.add(note);
        gson.writeToFile(notes);
        for (int i = 0; i < notes.size(); i++) {
            Assertions.assertEquals(notes.get(i).getTime().withNano(0), gson.readFromFile().get(i).getTime().withNano(0));
            Assertions.assertEquals(notes.get(i).getHeading(), gson.readFromFile().get(i).getHeading());
            Assertions.assertEquals(notes.get(i).getNote(), gson.readFromFile().get(i).getNote());
        }
    }
}