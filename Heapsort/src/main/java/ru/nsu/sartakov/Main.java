package ru.nsu.sartakov;

// get the variables (in string args)
// write the array
// make the heapsort

public class Main {
    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        Heapsort hs = new Heapsort();
        hs.sort(arr);



        // print the array
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "\n");
        }
    }
}