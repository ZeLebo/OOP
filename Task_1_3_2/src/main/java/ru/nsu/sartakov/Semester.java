package ru.nsu.sartakov;

import java.util.*;

public class Semester {
//    Semester() {};
    private final Map<String, Integer> semester = new HashMap<>();

    protected void setGrade(String course, int grade) {
        this.semester.put(course, grade);
    }

    protected Collection<String> getCourses() {
        return this.semester.keySet();
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

    public static boolean Diploma(Notebook notebook) {
        List<Integer> grades;
        grades = notebook.getAllGrades();
        long fives;
        boolean satis = grades.stream().anyMatch(g -> g < 4);
        fives = grades.stream().filter(g -> g == 5).count();
        double fivesPercent = (double) fives / grades.size();

        return fivesPercent >= 0.75 && !satis && notebook.getQualifyingWorkGrade() == 5;

    }

    public static boolean hasNoThree(Notebook notebook, int semester) {
        Collection<Integer> marks = notebook.getSemesterGrades(semester);
        boolean result = true;
        for (int mark : marks) {
            if (mark < 4) {
                result = false;
                break;
            }
        }
        return result;
    }

}
