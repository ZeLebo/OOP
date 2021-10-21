package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Semester {
    private final Map<String, Integer> semester = new HashMap<>();

    protected void setGrade(String course, int grade) {
        this.semester.put(course, grade);
    }

    protected Integer getCourseGrade(String course) {
        return this.semester.get(course);
    }

    protected ArrayList<String> getInfo() {
        ArrayList<String> gradesInfo = new ArrayList<>();
        for (String key : semester.keySet()) {
            gradesInfo.add(key + " - " + semester.get(key));
        }
        return gradesInfo;
    }

    protected Collection<Integer> getGrades() {
        return this.semester.values();
    }
}