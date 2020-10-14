package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MovingCount {

    private class Loc{
        int x;
        int y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int movingCount(int m, int n, int k) {

        if (k == 0) return 1;
        boolean[][] canVisit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num1 = i, num2 = j, sum = 0;
                while (num1 != 0) {
                    sum += num1 % 10;
                    num1 /= 10;
                }
                while (num2 != 0) {
                    sum += num2 % 10;
                    num2 /= 10;
                }
                if (sum <= k) canVisit[i][j] = true;
                else canVisit[i][j] = false;
            }
        }

        int count = 0;
        Loc loc = new Loc(0, 0);
        Queue<Loc> visitQueue = new LinkedList<Loc>();
        visitQueue.add(loc);
        canVisit[0][0] = false;
        while (!visitQueue.isEmpty()) {
            Loc temp = visitQueue.poll();
            ++count;
            // 下
            if (temp.x < m - 1 && canVisit[temp.x + 1][temp.y]) {
                Loc newLoc = new Loc(temp.x + 1, temp.y);
                visitQueue.add(newLoc);
                canVisit[temp.x + 1][temp.y] = false;
            }
            // 右
            if (temp.y < n - 1 && canVisit[temp.x][temp.y + 1]) {
                Loc newLoc = new Loc(temp.x, temp.y + 1);
                visitQueue.add(newLoc);
                canVisit[temp.x][temp.y + 1] = false;
            }
        }
        return count;
    }
}
