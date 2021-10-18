package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Notebook {
    protected String fullName;
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

    public static boolean diploma(Notebook notebook) {
        List<Integer> grades = notebook.getAllGrades();

        boolean isSatisfactoryGrades = grades.stream().anyMatch(g -> g < 4);
        long fives = grades.stream().filter(g -> g == 5).count();
        double fivesPercent = (double) fives / grades.size();
        return fivesPercent >= 0.75 && !isSatisfactoryGrades && notebook.getQualifyingWorkGrade() == 5;
    }

    public void add(int semester, String course, int grade) {
        this.semesters[semester].setGrade(course, grade);
    }

    public static boolean hasNoThree(Notebook notebook, int semester) {
        Collection<Integer> marks = notebook.getSemesterGrades(semester);
        for (int mark : marks) {
            if (mark < 4) {
                return false;
            }
        }
        return true;
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

    public void nameChanging(String newName) {
        this.fullName = newName;
    }

    public Collection<Integer> getSemesterGrades(int semester) {
        return semesters[semester].getGrades();
    }
}