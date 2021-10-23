package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Notebook {
    /**
     * I used "protested" cause people may change their names
     */
    public String fullName;
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

    public double allTimeAverageScore() {
        double result = 0.0;
        for (int i = 1; i < this.getSemNumber(); i++) {
            result += this.semesterAverageScore(i);
        }
        result /= this.getSemNumber() - 1;
        return result;
    }

    /**
     * get the info about having of not "satisfactory" in semester
     * @param semester the number of semester you want to get
     * @return the statement whether you have "three" or not
     */
    public boolean hasNoThreeInSemester(int semester) {
        Collection<Integer> marks = this.getSemesterGrades(semester);
        for (int mark : marks) {
            if (mark < 4) {
                return false;
            }
        }
        return true;
    }

    /**
     * you can get scholarschip if you don't have "satisfactory"
     * @param semester the number of semester you want to know if you got the scholarship
     * @return the state, whether you get it or not
     */
    public boolean canGetScholarship(int semester) {
        return hasNoThreeInSemester(semester);
    }

    /**
     * Maybe you have the grade book and want to know if the person has scholarship or not
     * And let me think that all the people on the first semester gets the scholarship
     */
    public boolean isHavingScholarship() {
        int actualSem = getSemNumber();
        if (actualSem == 1) {
            return true;
        }
        return canGetScholarship(actualSem - 1);
    }

    /**
     * Let me make it as the first semester students can't have higher scholarship
     * @return whether the person have the
     */
    public boolean isHavingHigherScholarship() {
        int actualSem = getSemNumber();
        if (actualSem == 1) {
            return false;
        }
        Collection<Integer> marks = this.getSemesterGrades(actualSem - 1);
        for (int mark : marks) {
            if (mark < 5) {
                return false;
            }
        }
        return true;
    }

    public void add(int semester, String course, int grade) {
        this.semesters[semester].setGrade(course, grade);
    }

    public int getQualifyingWorkGrade() {
        return this.qualifyingWorkGrade;
    }

    public void setQualifyingWorkGrade(int qualifyingWorkGrade) {
        this.qualifyingWorkGrade = qualifyingWorkGrade;
    }

    public Collection<Integer> getSemesterGrades(int semester) {
        return semesters[semester].getGrades();
    }

    public void changeName(String newName) {
        this.fullName = newName;
    }

    /**
     * if you want to get the special course grade
     * @param semester the number of the semester
     * @param course   the name of the course attended
     * @return the grade
     */
    public int getSemesterCourseGrade(int semester, String course) {
        return semesters[semester].getCourseGrade(course);
    }

    public ArrayList<String> getSemesterInfo(int semester) {
        return semesters[semester].getInfo();
    }

    /**
     * During studying we get the grades,
     * so if we don't have any grades in semester
     * it means this is one we're studying
     * if we don't have grades at all,
     * @return 1 we are on the first one
     */
    public int getSemNumber() {
        for (int i = 1; i <= SEM_AMOUNT; i++) {
            Collection<Integer> grades = getSemesterGrades(i);
            if (grades.isEmpty()) {
                return i;
            }
        }
        return 1;
    }

    /**
     * if you want to get the special semester average score
     * @param semester the number of semester you want to get grades of
     * @return the average score of semester
     */
    public double semesterAverageScore(int semester) {
        Collection<Integer> grades = getSemesterGrades(semester);
        double result = 0;
        for (int mark : grades)
            result += mark;
        result /= grades.size();
        return result;
    }

    /**
     * Obvious that if you want to get the whole course ( till your actual semester )
     * average score, you can do this
     * @return average score for all the time
     */
    public ArrayList<Integer> getAllGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 1; i <= SEM_AMOUNT; i++) {
            grades.addAll(semesters[i].getGrades());
        }
        return grades;
    }

    /**
     * to have red diploma you have to have at least 75% of grades "five"
     * you can have "satisfactory" during the studying
     * you have to get "excellent" for the qualifying work
     * @return whether you can have the red diploma or not
     */
    public boolean isRedDiploma() {
        List<Integer> grades = this.getAllGrades();
        boolean isSatisfactoryGrades = grades.stream().anyMatch(g -> g < 4);
        long fives = grades.stream().filter(g -> g == 5).count();
        double fivesPercent = (double) fives / grades.size();
        return fivesPercent >= 0.75 && !isSatisfactoryGrades && this.getQualifyingWorkGrade() == 5;
    }
}