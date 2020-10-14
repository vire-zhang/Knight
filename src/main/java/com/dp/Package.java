package com.dp;

public class Package {

    public static int maxValue(int[] w, int[] v, int capcity) {
        int[][] dp = new int[capcity + 1][w.length + 1];
        for (int i = 1; i <= capcity; i++) {
            for (int j = 1; j <= v.length; j++) {
                if (i >= w[j - 1])
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - w[j - 1]][j - 1] + v[j - 1]);
                else
                    dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[capcity][w.length];
    }

    public static void main(String[] args) {
        System.out.println(Package.maxValue(new int[]{2,3,4,5}, new int[]{3,4,5,6}, 8));
    }
}
