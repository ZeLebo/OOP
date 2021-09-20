package ru.nsu.sartakov;

public class Main {
    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        int n = arr.length;

        Heapsort ob = Heapsort.sort(arr);
        Heapsort.sort(arr);
        // print the array
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i] + "\n");
        }
    }
}