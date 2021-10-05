package ru.nsu.sartakov;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

public class ZFunc {
    private static final int BUF_LENGTH = 2048;

    public static StringBuilder startFunc(String file_name, File file, String pattern) throws IOException {
        StringBuilder res = new StringBuilder();

        if (!file.exists()) {
            res.append("There's no such file");
            return res;
        } else if (file.length() == 0) {
            res.append("The file is empty");
            return res;
        }
        else if ( pattern.length() == 0) {
            res.append("I don't know what to find");
            return res;
        }
        else {
            char[] buf = new char[BUF_LENGTH];
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_name), StandardCharsets.UTF_8), BUF_LENGTH);
            for (int shift = 0; shift < (int) file.length(); shift += BUF_LENGTH - pattern.length()) {
                int charsRead = reader.read(buf);
                String toCheck = new String(Arrays.copyOf(buf, shift + charsRead - 1));
                res.append(search(shift, toCheck, pattern));
            }
            if (res.length() != 0) {
                res.insert(0, '{');
                res.setCharAt((res.length() - 1), '}');
            }
            else {
                res.append("{}");
            }
            return res;
        }
    }

    public static StringBuilder search(int shift, String text, String pattern) {
        StringBuilder res = new StringBuilder();
        String concat = pattern + "$" + text;
        int[] Z = getZarr(concat);

        for (int i = 0; i < concat.length(); ++i) {
            if (Z[i] == pattern.length()) {
                res.append(shift + i - pattern.length() - 1).append(" ");
            }
        }
        return res;
    }

    private static int[] getZarr(String str) {
        int[] Z = new int[str.length()];
        int n = str.length();
        int L = 0, R = 0;

        for (int i = 1; i < n; i++) {
            Z[i] = (R > i) ? min(Z[i - L], R - i) : 0;
            while ((i + Z[i] < n) && str.charAt(Z[i]) == str.charAt(i + Z[i])) {
                ++Z[i];
            }
            if (i + Z[i] > R) {
                L = i;
                R = i + Z[i];
            }
        }
        return Z;
    }

    public static void main(String[] args) throws IOException {
        // IO scanner and some checkups
        Scanner input = new Scanner(System.in);
        String file_name = input.nextLine();

        if (file_name.length() == 0) {
            System.out.println("Enter something UwU");
            return;
        }
        // rename for specific directory
        file_name = "src/files/" + file_name;
        File file = new File(file_name);
        String pattern = input.nextLine();
        // entry point and something to output
        StringBuilder res = startFunc(file_name, file, pattern);
        System.out.println(res);
    }
}
