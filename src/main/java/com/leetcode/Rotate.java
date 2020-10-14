package com.leetcode;

public class Rotate {

    public static void rotate(int[][] matrix) {
        int count = -1;
        int n = matrix[0].length;
        for (int round = n - 1; round > 0; round -= 2) {
            ++count;
            for (int i = 0; i < round; i++) {
                int temp = matrix[count + i][count];
                matrix[count + i][count] = matrix[n - 1 - count][count + i];
                matrix[n - 1 - count][count + i] = matrix[n - 1 - count - i][n - 1 - count];
                matrix[n - 1 - count - i][n - 1 - count] = matrix[count][n - 1 - count - i];
                matrix[count][n - 1 - count - i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        rotate(m);
    }
}
