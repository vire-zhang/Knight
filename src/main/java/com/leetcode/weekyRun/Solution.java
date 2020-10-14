package com.leetcode.weekyRun;

class Solution {
    public static int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] ret = new int[n][m][m];
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < m;j++) {
                for (int k = 0;k < m;k++) {
                    ret[i][j][k] = -1;
                }
            }
        }
        if (m == 1) {
            ret[0][0][0] = grid[0][0];
        } else {
            ret[0][0][m - 1] = grid[0][0] + grid[0][m - 1];
        }

        for (int i = 1;i < n;i++) {
            for (int j = 0;j < m;j++) {
                for (int k = j;k < m;k++) {
                    if (ret[i - 1][j][k] == -1) {
                        continue;
                    }

                    for (int x = -1;x <= 1;x++) {
                        if (j + x < 0 || j + x >= m) {
                            continue;
                        }
                        for (int y = -1;y <= 1;y++) {
                            if (k + y < 0 || k + y >= m) {
                                continue;
                            }

                            int xx = j + x;
                            int yy = k + y;
                            if (xx > yy) {
                                int t = xx;
                                xx = yy;
                                yy = t;
                            }
                            int v = 0;
                            if (xx == yy) {
                                v = grid[i][xx];
                            } else {
                                v = grid[i][xx] + grid[i][yy];
                            }

                            if (ret[i][xx][yy] == -1) {
                                ret[i][xx][yy] = ret[i - 1][j][k] + v;
                            } else {
                                ret[i][xx][yy] = Math.max(ret[i][xx][yy], ret[i - 1][j][k] + v);
                            }
                        }
                    }
                }
            }
        }

        int p = 0;
        for (int j = 0;j < m;j++) {
            for (int k = j;k < m;k++) {
                p = Math.max(p, ret[n - 1][j][k]);
            }
        }
        return p;
    }

    public static void main(String[] args) {
        System.out.println(Solution.cherryPickup(new int[][] {{3,1,1},{2,5,1},{1,5,5},{2,1,1}}));
    }
}
