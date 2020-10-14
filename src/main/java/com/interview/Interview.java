package com.interview;

import java.util.Arrays;

public class Interview {
    public static void main(String[] args) {
        int[] arr = new int[] {4,3,5,8,1,7,2,9,10,68,34,22,15};
        Interview interview = new Interview();
        System.out.println(interview.quickSort(arr));
    }

    public int[] quickSort(int[] arr) {
        return qSort(arr, 0, arr.length - 1);
    }
    private int[] qSort(int[] arr, int left, int right) {
        int partitionIndex;
        if (left < right) {
            partitionIndex = partition(arr, left, right);
            qSort(arr, left, partitionIndex - 1);
            qSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }
    private int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
