package com.leetcode;

public class MinDistance {

    public int minDistance(String word1, String word2) {

        int m = word1.length(), n = word2.length();
        int[][] ed = new int[m + 1][n + 1];
        for (int i = 0; i < n + 1; i++) ed[0][i] = i;
        for (int i = 1; i < m + 1; i++) ed[i][0] = i;
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (chars1[i - 1] == chars2[j - 1])
                    ed[i][j] = Math.min(ed[i - 1][j - 1], Math.min(ed[i - 1][j] + 1, ed[i][j - 1] + 1));
                else
                    ed[i][j] = Math.min(ed[i - 1][j - 1] + 1, Math.min(ed[i - 1][j] + 1, ed[i][j - 1] + 1));
            }
        }
        return ed[m][n];
    }
}
