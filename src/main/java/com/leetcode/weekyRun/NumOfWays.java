package com.leetcode.weekyRun;

import java.util.ArrayList;
import java.util.List;

public class NumOfWays {

    private static final double MOD = (Math.pow(10, 9) + 7);
//
//    public int numOfWays(int n) {
//
//        if (n == 1) return 12;
//        double res = 6 * (left(n - 2) + right(n - 2)) % MOD;
//        return (int)res;
//    }
//
//    private int left(int n) {
//        if (n == 0) return 5;
//        double res = (3 * left(n - 1) + 2 * right(n - 1)) % MOD;
//        return (int) res;
//    }
//
//    private int right(int n) {
//        if (n == 0) return 4;
//        double res = (2 * left(n - 1) + 2 * right(n - 1)) % MOD;
//        return (int) res;
//    }

    public int numOfWays(int n) {
        if (n == 1) return 12;
        double l = 5, r = 4;
        for (int i = n - 1; i > 1; i--) {
            double temp = (l * 3 + r * 2) % MOD;
            r = (l * 2 + r * 2) % MOD;
            l = temp;
        }
        double res = (6 * (l + r)) % MOD;
        return (int)res;
    }

    public int[] processQueries(int[] queries, int m) {

        int[] result = new int[queries.length];
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 1; i <= m; ++i) {
            arr.add(i);
        }
        int i = 0;
        for(int num : queries) {
            result[i++] = arr.indexOf(num);
            arr.remove(new Integer(num));
            arr.add(0, num);
        }

        return result;
    }

    public static void main(String[] args) {
        NumOfWays soulu = new NumOfWays();
//        System.out.println(soulu.numOfWays(20));
        for (int i : soulu.processQueries(new int[]{3, 1, 2, 1}, 5)) {
            System.out.println(i);
        }
    }
}
