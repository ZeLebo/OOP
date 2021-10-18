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
    public void AverageScoreTest() {
        ArrayList<Integer> grades = mine.getAllGrades();
        Assertions.assertEquals(4.1, Semester.averageScore(grades), 0.1);
    }

    @Test
    public void DiplomaTest() {
        mine.setQualifyingWorkGrade(5);
        Assertions.assertFalse(Notebook.diploma(mine));
    }

    @Test
    public void hasNoThreeTest() {
        Assertions.assertFalse(Notebook.hasNoThree(mine, 1));
    }

    @Test
    public void nameChangingTest() {
        Assertions.assertSame("SartakovAA", mine.fullName);
        mine.nameChanging("AzazaZA");
        Assertions.assertEquals("AzazaZA", mine.fullName);
    }
}