package javabook;

import java.util.Arrays;

public class LotteryArray {
    public static void main(String[] args) {
        final int NMAX = 10;
        int[][] odds = getOdds(NMAX);
        for (int[] row : odds) {
            for (int odd : row)
                System.out.printf("%4d", odd);
            System.out.println();
        }

        System.out.println();
        System.out.println(Arrays.deepToString(odds));
//        System.out.println("current position=" + position);
    }

    protected static int[][] getOdds(int n) {
        int[][] odds = new int[n + 1][];
        for (int i = 0; i <= n; i++)
            odds[i] = new int[i + 1];

        for (int i = 0; i < odds.length; i++) {
            for (int j = 0; j < odds[i].length; j++) {
                int lotteryOdds = 1;
                for (int k = 1; k <= j; k++)
                    lotteryOdds = lotteryOdds * (i - k + 1) / k;

                odds[i][j] = lotteryOdds;
            }
        }

        return odds;
    }
}
