package ru.nsu.sartakov;

import com.google.gson.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Notebook  {
    private final List<Note> notes;
    private final Gson data;
    String notesFile = "Notes.json";
    DateTimeFormatter dtf;

    Notebook() {
        notes = new ArrayList<>();
        data = new GsonBuilder().setPrettyPrinting().create();
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

    public void add(String text) {
        LocalDateTime now = LocalDateTime.now();
        Note newNote = new Note(dtf.format(now), text);
        notes.add(newNote);
    }

    public void remove(String text){
        notes.removeIf(note -> note.getNote().equals(text));
    }

    public String show(LocalDateTime after, LocalDateTime before,String keyWord) throws IOException {
        LocalDateTime date;
        List<Note> searchResult = new ArrayList<>();;
        for (Note note: notes) {
            date = LocalDateTime.parse(note.getTime(), dtf);
            String text = note.getNote();
            if((date.isAfter(after))&&(date.isBefore(before))&&(text.contains(keyWord))){
                searchResult.add(note);
            }
        }
        try (Writer writer = new FileWriter(notesFile)) {
            data.toJson(searchResult, writer);
        }
        return data.toJson(searchResult);
    }

    public String show() throws IOException {
        try (Writer writer = new FileWriter(notesFile)) {
            data.toJson(notes, writer);
        }
        return data.toJson(notes);
    }

}
