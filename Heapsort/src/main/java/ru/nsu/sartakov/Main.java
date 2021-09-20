package ru.nsu.sartakov;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int size = input.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the array:");
        for (int i = 0; i < size; i++) {
            arr[i] = input.nextInt();
        }

        Heapsort hs = new Heapsort();
        hs.sort(arr);

        // print the array
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1]);
    }
}