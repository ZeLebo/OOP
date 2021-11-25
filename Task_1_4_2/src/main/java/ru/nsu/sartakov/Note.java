package ru.nsu.sartakov;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Note {
    @SerializedName("creation_time")
    LocalDateTime time;
    @SerializedName("heading")
    private final String heading;
    @SerializedName("note")
    private final String note;

    Note(String heading, String note) {
        this.time = LocalDateTime.now();
        this.heading = heading;
        this.note = note;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getHeading() {
        return this.heading;
    }

    public String getNote() {
        return note;
    }
}