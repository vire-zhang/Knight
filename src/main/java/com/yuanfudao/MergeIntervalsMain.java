package com.yuanfudao;

public class MergeIntervalsMain {

    public static void main(String[] args) {
        int[][] input = {{5,6}, {2,7}, {8,10}, {15,18}};
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] output = mergeIntervals.merge(input);
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }
    }
}
