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

    protected Collection<Integer> getGrades() {
        return this.semester.values();
    }

    public static double averageScore(ArrayList<Integer> marks) {
        double result = 0.0;
        for (int mark : marks) {
            result += mark;
        }
        result /= marks.size();
        return result;
    }
}