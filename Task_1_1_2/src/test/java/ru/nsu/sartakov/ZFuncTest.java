package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ZFuncTest {
    private static void testFunc (String Text, String pattern) {

    }

    @Test
    public void ZFunc_emptyFile() throws IOException {
        String file_name = "src/files/empty.txt";
        String pattern = "something";
        File file = new File(file_name);
        StringBuilder res = ZFunc.startFunc(file_name, file, pattern);
        StringBuilder resEmpty = new StringBuilder();
        resEmpty.append("The file is empty");
        Assertions.assertSame(resEmpty, res);
    }

    @Test
    public void ZFunc_emptyPattern() throws IOException {
        String file_name = "src/files/test.txt";
        String pattern = "";
        File file = new File(file_name);
        StringBuilder res = ZFunc.startFunc(file_name, file, pattern);
        StringBuilder resNothing = new StringBuilder();
        resNothing.append("I don't know what to find");
        Assertions.assertSame(resNothing, res);
    }

    @Test
    public void ZFunc_bigFile() throws IOException {
        String file_name = "src/files/bigfile.txt";
        String pattern = "something";
        File file = new File(file_name);
        StringBuilder res = ZFunc.startFunc(file_name, file, pattern);
    }

    @Test
    public void ZFunc_normalFile() throws IOException {
        String file_name = "src/files/empty.txt";
        String pattern = "something";
        File file = new File(file_name);
        StringBuilder res = ZFunc.startFunc(file_name, file, pattern);
    }

    @Test
    public void ZFunc_noSubstrings() throws IOException {
        String file_name = "src/files/input.txt";
        String pattern = "hello";
        File file = new File(file_name);
        StringBuilder res = ZFunc.startFunc(file_name, file, pattern);
    }
}