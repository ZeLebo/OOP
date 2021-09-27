package ru.nsu.sartakov;

import java.util.Scanner;
import java.awt.Desktop;
import java.io.*;


// the func, that opens the file
// the func that searches the string
// z-function

public class Main {

    public static int search(String text, String pattern)
    {
        String concat = pattern + "$" + text;
        int l = concat.length();
        int Z[] = new int[l];
        getZarr(concat, Z);
        for(int i = 0; i < l; ++i){
            if(Z[i] == pattern.length()){
                return (i - pattern.length() - 1);
            }
        }
        return -1;
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
        // get the string
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String pattern = input.nextLine();

        if (str.isEmpty()){
            System.out.println("Enter something UwU");
            return;
        }

        String[] line = str.split(" ");
        str = "";

        for (int i = 1; i < line.length; i++) {
            str += line[i] + " ";
        }

        // open the file, if it exists
        File file = new File (line[0]);
        int l = -1;
        if (!file.exists()) {
            System.out.println("There's no such file");
            return;
        }
        l = search(str, pattern);
        // testing print (don't kick me)
        System.out.println("The filename is " + file);
        System.out.println("The stirng to find is " + str);
    }
}
