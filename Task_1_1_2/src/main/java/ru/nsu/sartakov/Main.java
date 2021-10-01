package ru.nsu.sartakov;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        String pattern = input.nextLine();

        if (file_name.length() == 0){
            System.out.println("Enter something UwU");
            return;
        }
        file_name = "src/files/" + file_name;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file_name), StandardCharsets.UTF_8), BUF_LENGTH);
        /*
            How to work with file as a string
            How to make a buffer reading
         */

        int l = ZFunc.search(reader.toString(), pattern);
        if (l == -1){
            System.out.println("No substring in that file\n or something went wrong");
        }
    }
}
