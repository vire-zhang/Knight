package com.leetcode.weekyRun;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrimMean {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int length = arr.length;
        int trim = length / 20;
        int sum = 0;
        for (int i = trim; i < arr.length - trim; i++) {
            sum += arr[i];
        }
        return (double) sum / (length - trim * 2);
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        if (towers.length == 1) {
            return new int[] {towers[0][0], towers[0][1]};
        }
        int[][] range = calculateRange(towers);
        return searchBestCoordinate(towers, radius, range);
    }
    private int[] searchBestCoordinate(int[][] towers, int radius, int[][] range) {
        int max = 0;
        int[] result = new int[2];
        for (int i = range[0][0]; i <= range[1][0]; i++) {
            for (int j = range[0][1]; j <= range[1][1]; j++) {
                int val = calculateCoordinate(i, j, towers, radius);
                if (val > max) {
                    max = val;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
    private int calculateCoordinate(int x, int y, int[][] towers, int radius) {
        int val = 0;
        for (int[] tower : towers) {
            double distance = calDistance(x, y, tower[0], tower[1]);
            if (distance <= radius) {
                val += tower[2] / (1 + distance);
            }
        }
        return val;
    }
    private double calDistance(int x, int y, int x2, int y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }
    private int[][] calculateRange(int[][] towers) {
        int xLeft = 50;
        int yDown = 50;
        int xRight = 0;
        int yUp = 0;
        for (int[] tower : towers) {
            if (tower[0] < xLeft) {
                xLeft = tower[0];
            }
            if (tower[0] > xRight) {
                xRight = tower[0];
            }
            if (tower[1] < yDown) {
                yDown = tower[1];
            }
            if (tower[1] > yUp) {
                yUp = tower[1];
            }
        }
        return new int[][] {{xLeft, yDown}, {xRight, yUp}};
    }

//    public int numberOfSets(int n, int k) {
//
//    }

    public static void main(String[] args) {
        TrimMean trimMean = new TrimMean();
        System.out.println(trimMean.trimMean(new int[]{6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4}));

        Fancy fancy = new Fancy();
        fancy.append(2);
        fancy.addAll(3);
        fancy.append(7);
        fancy.multAll(2);
        fancy.getIndex(0);
        fancy.addAll(3);
        fancy.append(10);
        fancy.multAll(2);
        fancy.getIndex(0);
        fancy.getIndex(1);
        fancy.getIndex(2);
    }
}

class Fancy {
    //TODO:考虑多线程，明天试试 2020-10-17 24:00

    private List<Integer> arr;
    private final int MOD = 1000000007;

    public Fancy() {
        arr = new ArrayList<>();
    }

    public void append(int val) {
        arr.add(val);
    }

    public void addAll(int inc) {
        for (int i = 0; i < arr.size(); i++) {
            int val = (arr.get(i) + inc) % MOD;
            arr.set(i, val);
        }
    }

    public void multAll(int m) {
        for (int i = 0; i < arr.size(); i++) {
            BigDecimal val = BigDecimal.valueOf(arr.get(i));
            val = val.multiply(BigDecimal.valueOf(m));
            val = val.remainder(BigDecimal.valueOf(MOD));
//            int result = 0;
//            for (int i1 = 0; i1 < m; i1++) {
//                result += val;
//                result %= MOD;
//            }
            arr.set(i, val.intValue());
        }
    }

    public int getIndex(int idx) {
        if (idx >= arr.size()) {
            return -1;
        }
        return arr.get(idx);
    }
}
