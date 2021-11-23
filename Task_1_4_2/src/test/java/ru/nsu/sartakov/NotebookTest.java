package ru.nsu.sartakov;

import org.junit.jupiter.api.Test;

import java.io.IOException;


public class NotebookTest {
    Notebook notebook = new Notebook();
    @Test
    public void addTest() {
        notebook.addNote("My new note", "Hello");
        notebook.addNote("The new heading", "The new note content");
    }

    @Test
    public void showAllNotes() throws IOException {
        notebook.addNote("My new note", "Hello");
        System.out.println(notebook.showAllNotes());
    }
}