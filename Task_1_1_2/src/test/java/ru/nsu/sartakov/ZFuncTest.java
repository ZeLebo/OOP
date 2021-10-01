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
}