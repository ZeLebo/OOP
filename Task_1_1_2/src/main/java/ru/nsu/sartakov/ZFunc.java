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
public class ZFunc {

    public static StringBuilder startFunc(String file_name, File file, String pattern) throws IOException {
        // reading the file in buffer
        final int BUF_LENGTH = 2048;
        char[] buf = new char[BUF_LENGTH];
        // Reader in UTF_8
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_name), StandardCharsets.UTF_8), BUF_LENGTH);
        StringBuilder res = new StringBuilder();
        for (int shift = 0; shift < (int) file.length(); shift += BUF_LENGTH - pattern.length()) {
            int charsRead = reader.read(buf);
            String toCheck = new String(Arrays.copyOf(buf, shift + charsRead - 1));
            res.append(search(shift, toCheck, pattern));
        }
        return res;
    }

    public static StringBuilder search(int shift, String text, String pattern) {
        StringBuilder res = new StringBuilder();
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
        for(int i = 1; i < n; ++i) {
            if(i > R){
                L = R = i;
                while(R < n && str.charAt(R - L) == str.charAt(R))
                    R++;
                Z[i] = R - L;
                R--;
            }
            else{
                int k = i - L;
                if(Z[k] < R - i + 1)
                    Z[i] = Z[k];
                else{
                    L = i;
                    while(R < n && str.charAt(R - L) == str.charAt(R))
                        R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String file_name = input.nextLine();

        if (file_name.length() == 0){
            System.out.println("Enter something UwU");
            return;
        }
        file_name = "src/files/" + file_name;

        File file = new File (file_name);
        if (!file.exists()) {
            System.out.println("There's no such file");
        }


        String pattern = input.nextLine();
        if ( pattern.length() == 0) {
            System.out.println("I don't know what to find");
            return;
        }
        StringBuilder res = startFunc(file_name, file, pattern);
        System.out.println(res);
    }
}
