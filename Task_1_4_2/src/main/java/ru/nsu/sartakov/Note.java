package ru.nsu.sartakov;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Note {
    @SerializedName("creation_time")
    private LocalDateTime time;
    @SerializedName("heading")
    private final String heading;
    @SerializedName("note")
    private final String note;

    Note(String heading, String note) {
        this.time = LocalDateTime.now();
        this.heading = heading;
        this.note = note;
    }

    /**
     * @return the date of note's creation
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @return the heading of the note
     */
    public String getHeading() {
        return this.heading;
    }

    /**
     * @return the note content
     */
    public String getNote() {
        return note;
    }
}