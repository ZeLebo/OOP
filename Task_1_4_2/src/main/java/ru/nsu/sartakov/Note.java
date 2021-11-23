package ru.nsu.sartakov;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    private transient LocalDateTime creationTimeNotFormatted;
    @SerializedName("creation_time")
    private String formattedTime;
    @SerializedName("heading")
    private String heading;
    @SerializedName("note")
    private String note;

    Note(){}

    transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Note(String heading, String note) {
        this.creationTimeNotFormatted = LocalDateTime.now();
        this.formattedTime = LocalDateTime.now().format(formatter);
        this.heading = heading;
        this.note = note;
    }

    public void setCreationTimeFormatted(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getCreationTimeFormatted() {
        return formattedTime;
    }

    public void setCreationTimeNotFormatted(LocalDateTime time) {
        this.creationTimeNotFormatted = time;
    }

    public LocalDateTime getCreationTimeNotFormatted() {
        return this.creationTimeNotFormatted;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return this.heading;
    }


    public String getNote() {
        return note;
    }

    public LocalDateTime getTime() {
        return creationTimeNotFormatted;
    }
}