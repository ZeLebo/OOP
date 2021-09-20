package ru.nsu.sartakov;

public class Heapsort{
    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    void heapify(int[] arr, int n, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = left + 1;

        if (left < n && arr[left] > arr[max]){
            max = left;
        }

        if (right < n && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) {
            int tmp_1 = arr[i];
            arr[i] = arr[max];
            arr[max] = tmp_1;

            heapify(arr, n, max);
        }
    }
}