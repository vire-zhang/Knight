package com.leetcode.weekyRun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSubsequence {

    public static List<Integer> minSubsequence(int[] nums) {

        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        result.add(nums[nums.length - 1]);
        if (nums.length == 1) return result;
        int remainSum = 0;
        int getSum = nums[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            remainSum += nums[i];
        }
        int loc = nums.length - 2;
        while (getSum <= remainSum) {
            getSum += nums[loc];
            remainSum -= nums[loc];
            result.add(nums[loc]);
            --loc;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {6};
        List<Integer> out = MinSubsequence.minSubsequence(nums);
    }
}

/**
 * 5376
 */
