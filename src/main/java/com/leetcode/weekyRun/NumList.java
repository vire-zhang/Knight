package com.leetcode.weekyRun;

import java.util.Arrays;

public class NumList {

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int delta = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != delta) return false;
        }
        return true;
    }

    public int getLastMoment(int n, int[] left, int[] right) {
        Arrays.sort(left);
        Arrays.sort(right);
        int l = left.length == 0 ? 0 : left[left.length - 1];
        int r = right.length == 0 ? 0 : n - right[0];
        return Math.max(l, r);
    }
}
