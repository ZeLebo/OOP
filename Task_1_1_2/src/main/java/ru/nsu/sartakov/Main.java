package ru.nsu.sartakov;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

/*
input.txt - file where to find
pattern - what to find
*/
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        String file_name = input.nextLine();
        if (file_name.length() == 0){
            System.out.println("Enter something UwU");
            return;
        }
        file_name = "src/files/" + file_name;

        String pattern = input.nextLine();
        if ( pattern.length() == 0) {
            System.out.println("I don't know what to find");
            return;
        }


        // checks if the file exits, if not create one
        File file = new File (file_name);
        if (!file.exists()) {
            System.out.println("There's no such file");
        }

        // reading the file in buffer
        final int BUF_LENGTH = 2048;
        char[] buf = new char[BUF_LENGTH];
        // Reader in UTF_8
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file_name), StandardCharsets.UTF_8), BUF_LENGTH);

        // shift + [array of chars] < file.len
        StringBuilder res = new StringBuilder();
        for (int shift = 0;
             shift < (int) file.length();
             shift += BUF_LENGTH - pattern.length()) {

            int charsRead = reader.read(buf);
            String toCheck = new String(Arrays.copyOf(buf, shift + charsRead - 1));
            res.append(ZFunc.search(shift, toCheck, pattern));
        }
        System.out.println(res);
    }
}
