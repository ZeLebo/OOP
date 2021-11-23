package ru.nsu.sartakov;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Note {
    transient DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");
    transient LocalDateTime creationTime = LocalDateTime.now();
    @SerializedName("creation_time")
    Gson timeToGson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, typeOfT, context) ->
                            LocalDateTime.parse(json.getAsString(), dateTimeFormatter.withLocale(Locale.ENGLISH)))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                    (src, typeOfSrc, context) -> new JsonPrimitive(dateTimeFormatter.format(src)))
            .setPrettyPrinting()
            .create();
    @SerializedName("heading")
    private String heading;
    @SerializedName("note")
    private String note;

    Note(){}
    Note(String heading, String note) {
        this.creationTime = creationTime;
        this.heading = heading;
        this.note = note;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
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
        return creationTime;
    }
}