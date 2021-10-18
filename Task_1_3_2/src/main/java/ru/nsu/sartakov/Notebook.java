package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Collection;

public class Notebook extends Semester {
    private String fullName;
    private static final int SEM_AMOUNT = 8;
    private final Semester[] semesters = new Semester[SEM_AMOUNT + 1];
    private int qualifyingWorkGrade;

    public Notebook(String Name, int qualifyingWorkGrade) {
        this.fullName = Name;
        this.qualifyingWorkGrade = qualifyingWorkGrade;
        for (int i = 1; i <= SEM_AMOUNT; i++) {
            Semester tmp = new Semester();
            semesters[i] = tmp;
        }
    }

    public void add(int semester, String course, int grade) {
        this.semesters[semester].setGrade(course, grade);
    }

    public Collection<Integer> getSemesterGrades(int course) {
        return semesters[course].getGrades();
    }

    public void setQualifyingWorkGrade(int qualifyingWorkGrade) {
        this.qualifyingWorkGrade = qualifyingWorkGrade;
    }

    public int getQualifyingWorkGrade() {
        return this.qualifyingWorkGrade;
    }

    public ArrayList<Integer> getAllGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 1; i <= SEM_AMOUNT; i++) {
            grades.addAll(semesters[i].getGrades());
        }
        return grades;
    }
}
