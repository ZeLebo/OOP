package ru.nsu.sartakov;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Note {
    @SerializedName("creation_time")
    LocalDateTime time;
    @SerializedName("heading")
    private String heading;
    @SerializedName("note")
    private String note;

    Note(){}

    Note(String heading, String note) {
        this.time = LocalDateTime.now();
        this.heading = heading;
        this.note = note;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return this.heading;
    }

    public void setNote(String content) {
        this.note = content;
    }

    public String getNote() {
        return note;
    }
}