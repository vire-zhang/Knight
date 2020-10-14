package com.yuanfudao;

import java.util.Arrays;

public class IslandMain {

    public static void main(String[] args) {
        int[][] island = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        int maxArea = maxAreaOfIsland.maxAreaOfIsland(island);
        System.out.println(maxArea);
    }
}
