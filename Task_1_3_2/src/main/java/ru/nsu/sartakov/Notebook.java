package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Collection;


public class Notebook extends Semester{

    private String fulName;
    private String course;
    private final Semester[] semesters = new Semester[9];
    private int qualifyingWorkGrade;

    public Notebook(String fulName, int qualifyingWorkGrade) {
        this.fulName = fulName;
        this.qualifyingWorkGrade = qualifyingWorkGrade;

        for (int i = 1; i < 9; i++) {
            Semester tmp = new Semester();
            semesters[i] = tmp;
        }
    }

    public Semester[] getSemesters() {
        return semesters;
    }

    public void setFulName(String fulName) {
        this.fulName = fulName;
    }

    public String getFulName() {
        return fulName;
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
        for (int i = 1; i < 9; i++) {
            grades.addAll(semesters[i].getGrades());
        }
        return grades;
    }
}
