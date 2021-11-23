package ru.nsu.sartakov;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.Notebook;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class NotebookTest {
    Notebook notebook = new Notebook();
    @Test
    public void addTest() throws IOException {
        notebook.add("My new note", "Hello");
        notebook.add("The new heading", "The new note content");
    }
}