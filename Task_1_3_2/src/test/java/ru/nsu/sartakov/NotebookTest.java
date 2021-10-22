package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class NotebookTest {
    Notebook mine = new Notebook("SartakovAA", 5);

    @BeforeEach
    void notebook_init() {
        mine.add(1, "Algebra", 4);
        mine.add(1, "Discrete", 4);
        mine.add(1, "Declarative", 3);
        mine.add(1, "Imperative", 3);
        mine.add(1, "Russian", 5);
        mine.add(1, "History", 5);

        mine.add(2, "Algebra", 3);
        mine.add(2, "Discrete", 5);
        mine.add(2, "Declarative", 4);
        mine.add(2, "Imperative", 4);
        mine.add(2, "English", 5);
        mine.add(2, "DP", 4);
    }

    @Test
    public void getQualifyingMark() {
        Assertions.assertSame(mine.getQualifyingWorkGrade(), 5);
    }

    @Test
    public void allTimeAverageScoreTest() {
        Assertions.assertEquals(4.1, mine.allTimeAverageScore(), 0.1);
    }

    @Test
    public void isRedDiplomaTest() {
        mine.setQualifyingWorkGrade(5);
        Assertions.assertFalse(mine.isRedDiploma());
    }

    @Test
    public void hasNoThreeTestPositive() {
        mine.add(1, "Declarative", 5);
        mine.add(1, "Imperative", 5);
        mine.add(2, "Algebra", 5);
        Assertions.assertTrue(mine.hasNoThreeInSemester(1));
        Assertions.assertTrue(mine.hasNoThreeInSemester(2));
    }

    @Test
    public void hasNoThreeNegativeTest() {
        Assertions.assertFalse(mine.hasNoThreeInSemester(1));
    }

    @Test
    public void canGetScholarschipTest() {
        Assertions.assertFalse(mine.canGetScholarship(1));
        Assertions.assertFalse(mine.canGetScholarship(2));
    }

    @Test
    public void isHavingScholarshipTest() {
        Assertions.assertFalse(mine.isHavingScholarship());
        mine.add(2, "Algebra", 5);
        Assertions.assertTrue(mine.isHavingScholarship());
    }

    @Test
    public void isHavingHigherScholarshipTest() {
        Assertions.assertFalse(mine.isHavingHigherScholarship());
        mine.add(2, "Algebra", 5);
        mine.add(2, "Discrete", 5);
        mine.add(2, "Declarative", 5);
        mine.add(2, "Imperative", 5);
        mine.add(2, "English", 5);
        mine.add(2, "DP", 5);
        Assertions.assertTrue(mine.isHavingHigherScholarship());
    }

    @Test
    public void changeNameTest() {
        Assertions.assertSame("SartakovAA", mine.fullName);
        mine.changeName("AzazaZA");
        Assertions.assertEquals("AzazaZA", mine.fullName);
    }

    @Test
    public void semesterAverageScoreTest() {
        Assertions.assertEquals(mine.semesterAverageScore(1), 4.0, 0.1);
        Assertions.assertEquals(mine.semesterAverageScore(2), 4.2, 0.1);
    }

    @Test
    public void getSemNumberTest() {
        Assertions.assertEquals(mine.getSemNumber(), 3);
    }

    @Test
    public void getSemesterCourseGradeTest() {
        Assertions.assertEquals(mine.getSemesterCourseGrade(1, "Algebra"), 4);
        mine.add(1, "Algebra", 5);
        Assertions.assertNotEquals(mine.getSemesterCourseGrade(1, "Algebra"), 4);
        Assertions.assertEquals(mine.getSemesterCourseGrade(1, "Algebra"), 5);
    }

    @Test
    public void getSemesterInfoTest() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Algebra - 4");
        info.add("Russian - 5");
        info.add("Discrete - 4");
        info.add("Declarative - 3");
        info.add("History - 5");
        info.add("Imperative - 3");

        Assertions.assertEquals(mine.getSemesterInfo(1), info);
    }
}