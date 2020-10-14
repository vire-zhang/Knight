package com.yuanfudao;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {

        int maxArea = 0;
        int area = 0;
        // 坐标队列
        Queue<Integer> i_loc = new LinkedList<>();
        Queue<Integer> j_loc = new LinkedList<>();
        int[][] gridClone = grid;
        for (int i = 0; i < gridClone.length; i++) {
            for (int j = 0; j < gridClone[i].length; j++) {
                if (gridClone[i][j] == 1) {
                    i_loc.add(i);
                    j_loc.add(j);
                    gridClone[i][j] = 0;
                }
                while (!i_loc.isEmpty()) {
                    int iTemp = i_loc.poll();
                    int jTemp = j_loc.poll();
                    ++area;
                    // 上
                    if (iTemp > 0 && gridClone[iTemp - 1][jTemp] == 1){
                        i_loc.add(iTemp - 1);
                        j_loc.add(jTemp);
                        gridClone[iTemp - 1][jTemp] = 0;
                    }
                    // 下
                    if (iTemp < gridClone.length - 1 && gridClone[iTemp + 1][jTemp] == 1) {
                        i_loc.add(iTemp + 1);
                        j_loc.add(jTemp);
                        gridClone[iTemp + 1][jTemp] = 0;
                    }
                    // 左
                    if (jTemp > 0 && gridClone[iTemp][jTemp - 1] == 1) {
                        i_loc.add(iTemp);
                        j_loc.add(jTemp - 1);
                        gridClone[iTemp][jTemp - 1] = 0;
                    }
                    // 右
                    if (jTemp < gridClone[0].length - 1 && gridClone[iTemp][jTemp + 1] == 1) {
                        i_loc.add(iTemp);
                        j_loc.add(jTemp + 1);
                        gridClone[iTemp][jTemp + 1] = 0;
                    }
                }
                if (area > maxArea) maxArea = area;
                area = 0;
            }
        }
        
        return maxArea;
    }
}
