package ru.nsu.sartakov;

import com.google.gson.annotations.SerializedName;
public class Note {
    @SerializedName("header")
    private String header = new String();
    @SerializedName("note")
    private String note = new String();
    @SerializedName("creationTime")
    private String creationTime = new String();

    Note(String time, String note) {
        this.creationTime = time;
        this.note = note;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
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