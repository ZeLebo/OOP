package ru.nsu.sartakov;

//import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
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

            // to create files for tests
            if (file.createNewFile()) {
                System.out.println("I've made one");
                FileWriter myWriter = new FileWriter(file_name);
                myWriter.write(pattern);
                myWriter.close();
            }

            return;
        }

        // reading the file in buffer
        final int BUF_LENGTH = 2048;
        char[] buf = new char[BUF_LENGTH];
        // Reader in UTF_8
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file_name), StandardCharsets.UTF_8), BUF_LENGTH);

        // shift + [array of chars] < file.len
        int res = 0;
        for (int shift = 0;
             shift < (int) file.length();
             shift += BUF_LENGTH - pattern.length()) {

            int charsRead = reader.read(buf);
            String toCheck = new String(Arrays.copyOf(buf, shift + charsRead - 1));
            res += ZFunc.search(shift, toCheck, pattern);
        }
        // out for testing
        if (res == 0) {
            System.out.println("\nNo substring found");
        }
        if (res == 1) {
            System.out.println("\n1 substring is found");
        }
        else {
            System.out.println("\n" + res + " substrings are found");
        }
    }
}
