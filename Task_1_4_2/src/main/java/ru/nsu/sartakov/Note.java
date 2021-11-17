package ru.nsu.sartakov;

import com.google.gson.annotations.SerializedName;

public class Note {
    @SerializedName("note")
    private String note;
    @SerializedName("creationTime")
    private String creationTime;

    Note(String time, String note) {
        this.creationTime = time;
        this.note = note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setTime(String time) {
        this.creationTime = time;
    }

    public String getTime() {
        return creationTime;
    }
}