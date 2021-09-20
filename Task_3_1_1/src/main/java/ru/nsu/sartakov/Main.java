package ru.nsu.sartakov;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList <Integer> nums = new ArrayList<> ();

        String str = input.nextLine();
        String[] line = str.split(",");

        for (String intStr : line) {
            if (!intStr.isEmpty()) {
                nums.add(Integer.parseInt(intStr));
            }
        }

        Integer[] arr = nums.toArray(new Integer[0]);

        Heapsort hs = new Heapsort();
        hs.sort(arr);

        // print the array
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1]);
    }
}