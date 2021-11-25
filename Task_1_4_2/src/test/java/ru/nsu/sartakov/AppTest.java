package ru.nsu.sartakov;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private App app = new App();
    private Notebook notebook = new Notebook();
    @Test
    public void parseTest() throws ParseException, IOException {
        notebook.removeNote("Header");
        String[] line = {"-add", "Header", "Content"};
        Note note = new Note("Header", "Content");
        app.mainCaller(line);
        Note result = notebook.showAllNotes().get(notebook.showAllNotes().size() - 1);
        Assertions.assertEquals(note.getTime().withNano(0), result.getTime().withNano(0));
        Assertions.assertEquals(note.getHeading(), result.getHeading());
        Assertions.assertEquals(note.getNote(), result.getNote());
    }
}