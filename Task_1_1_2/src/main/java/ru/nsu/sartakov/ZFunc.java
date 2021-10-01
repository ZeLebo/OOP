package ru.nsu.sartakov;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.SplittableRandom;

/*
input.txt - file where to find
pattern - what to find
*/
public class ZFunc {
    public static StringBuilder startFunc(String file_name, File file, String pattern) throws IOException {
        StringBuilder res = new StringBuilder();

        // some checkups for input
        if (!file.exists()) {
            String resNoFile = "There's no such file";
            res.append(resNoFile);
            return res;
        }
        if ( pattern.length() == 0) {
            String resNothing = "I don't know what to find";
            res.append(resNothing);
            return res;
        }

        if (file.length() == 0) {
            String resEmpty = "The file is empty";
            res.append(resEmpty);
            return res;
        }

        // reading the file in buffer
        final int BUF_LENGTH = 2048;
        char[] buf = new char[BUF_LENGTH];
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_name), StandardCharsets.UTF_8), BUF_LENGTH);
        // make it work with shifts
        for (int shift = 0; shift < (int) file.length(); shift += BUF_LENGTH - pattern.length()) {
            int charsRead = reader.read(buf);
            // start the search function ( ZFunc logic )
            String toCheck = new String(Arrays.copyOf(buf, shift + charsRead - 1));
            res.append(search(shift, toCheck, pattern));
        }
        res.insert(0, '{');
        res.setCharAt((res.length() - 1), '}');
        return res;
    }

    public static StringBuilder search(int shift, String text, String pattern) {
        StringBuilder res = new StringBuilder();
        // making it one string
        String concat = pattern + "$" + text;
        int[] Z = new int[concat.length()];
        getZarr(concat, Z);
        for(int i = 0; i < concat.length(); ++i){
            if(Z[i] == pattern.length()){
                res.append(shift + i - pattern.length() - 1).append(" ");
            }
        }
        return res;
    }

    private static void getZarr(String str, int[] Z) {
        int n = str.length();
        int L = 0, R = 0;
        for(int i = 1; i < n; i++) {
            if (i > R) {
                L = i;
                for (R = i; R < n && str.charAt(R - L) == str.charAt(R); R++);
                Z[i] = R - L;
                R--;
            }
            else {
                int k = i - L;
                if ( Z[k] < R - i + 1 )
                    Z[i] = Z[k];
                else{
                    L = i;
                    for ( ; R < n && str.charAt(R - L) == str.charAt(R); R++);
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // IO scanner and some checkups
        Scanner input = new Scanner(System.in);
        String file_name = input.nextLine();

        if (file_name.length() == 0){
            System.out.println("Enter something UwU");
            return;
        }
        // rename for specific directory
        file_name = "src/files/" + file_name;
        File file = new File (file_name);
        String pattern = input.nextLine();
        // entry point and something to output
        StringBuilder res = startFunc(file_name, file, pattern);
        System.out.println(res);
    }
}
