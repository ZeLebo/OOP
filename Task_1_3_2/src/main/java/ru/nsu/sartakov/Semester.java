package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Semester {
    private final Map<String, Integer> semester = new HashMap<>();

    public void setGrade(String course, int grade) {
        this.semester.put(course, grade);
    }

    public Integer getCourseGrade(String course) {
        return this.semester.get(course);
    }

    /**
     * To be honest I wanted to make it protected
     * In order to not everybody can get the info,
     * But I messed up =(
     *
     * @return ArrayList of string with course and grade for it
     */
    public ArrayList<String> getInfo() {
        ArrayList<String> gradesInfo = new ArrayList<>();
        for (String key : semester.keySet()) {
            gradesInfo.add(key + " - " + semester.get(key));
        }
        return gradesInfo;
    }

    public Collection<Integer> getGrades() {
        return this.semester.values();
    }
}