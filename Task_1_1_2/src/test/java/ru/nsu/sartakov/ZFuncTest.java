package ru.nsu.sartakov;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ZFuncTest {
    @Test
    public void ZFunc_emptyFile() throws IOException {
        String file_name = "src/files/empty.txt";
        String pattern = "something";
        File file = new File(file_name);
        String resEmpty = "The file is empty";
        String res = (ZFunc.startFunc(file_name, file, pattern)).toString();
        assertEquals(resEmpty, res);
    }

    @Test
    public void ZFunc_notExistingFile() throws IOException {
        String file_name = "src/files/none.txt";
        String pattern = "Lucifer is a great series";
        File file = new File(file_name);
        String resNotExist = "There's no such file";
        String res = (ZFunc.startFunc(file_name, file, pattern)).toString();
        assertEquals(resNotExist, res);
    }

    @Test
    public void ZFunc_emptyPattern() throws IOException {
        String file_name = "src/files/test.txt";
        String pattern = "";
        File file = new File(file_name);
        String res = (ZFunc.startFunc(file_name, file, pattern)).toString();
        String resNothing = "I don't know what to find";
        assertEquals(resNothing, res);
    }
    @Test
    public void ZFunc_bigFile() throws IOException {
        String file_name = "src/files/bigfile.txt";
        String pattern = "something";
        File file = new File(file_name);
        String testFuncRes = "{2205 2263 4602 6674 8326 9850 11095 11860 12745}";
        String res = (ZFunc.startFunc(file_name, file, pattern)).toString();
        assertEquals(testFuncRes, res);

    }

    @Test
    public void ZFunc_normalFile() throws IOException {
        String file_name = "src/files/input.txt";
        String pattern = "dear";
        File file = new File(file_name);
        String testFuncRes = "{10}";
        String res = (ZFunc.startFunc(file_name, file, pattern)).toString();
        assertEquals(testFuncRes, res);
    }

    @Test
    public void ZFunc_russian() throws IOException {
        String file_name = "src/files/russian.txt";
        String pattern = "пирог";
        File file = new File(file_name);
        String testFuncRes = "{7}";
        String res = (ZFunc.startFunc(file_name, file, pattern)).toString();
        assertEquals(testFuncRes, res);
    }

    @Test
    public void ZFunc_noSubstrings() throws IOException {
        String file_name = "src/files/input.txt";
        String pattern = "you can't find me there)";
        File file = new File(file_name);
        String testFuncRes = "{}";
        String res = (ZFunc.startFunc(file_name, file, pattern)).toString();
        assertEquals(testFuncRes, res);
    }
}