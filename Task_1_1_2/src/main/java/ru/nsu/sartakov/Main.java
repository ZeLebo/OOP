package ru.nsu.sartakov;

import java.io.File;
import java.util.Scanner;


/*
It's not done yet, it's just a draft, I will complete it in the morning,
and if I don't it means that I feel shattered or dead
please, don't kick or ban me
*/

// the func, that opens the file
// the func that searches the string
// z-function

public class Main {

    public static int search(String text, String pattern)
    {
        String concat = pattern + "$" + text;
        int l = concat.length();
        int[] Z = new int[l];
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


    public static void main(String[] args) {
        // get the string
        Scanner input = new Scanner(System.in);
        StringBuilder str = new StringBuilder(input.nextLine());
        String pattern = input.nextLine();

        if (str.length() == 0){
            System.out.println("Enter something UwU");
            return;
        }

        String[] line = str.toString().split(" ");
        str = new StringBuilder();

        for (int i = 1; i < line.length; i++) {
            str.append(line[i]).append(" ");
        }

        // open the file, if it exists
        File file = new File (line[0]);
        int l;
        if (!file.exists()) {
            System.out.println("There's no such file");
            return;
        }
        l = search(str.toString(), pattern);
        if (l == -1){
            System.out.println("no substring in that file\n or something went wrong");    
        }
        // testing print (don't kick me)
        System.out.println("The filename is " + file);
        System.out.println("The string to find is " + str);
    }
}
