package com.leetcode;

import org.w3c.dom.Node;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CanPartition {

    public boolean canPartition(int[] nums) {

        int n = nums.length;
        if (n == 1) return false;

        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;

        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++)
            for (int j = sum; j >= 0; j--) {
                if (dp[sum]) return true;
                if (j - nums[i] >= 0)
                    dp[j] = dp[j] || dp[j - nums[i]];
            }

        return dp[sum];
    }

    public boolean canPartitionError(int[] nums) {

        if (nums.length == 1) return false;

        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;

        Arrays.sort(nums);
        int group1 = 0;
        int group2 = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            group1 += nums[i];
            if (group1 == sum / 2) return true;
            if (group1 > sum / 2 || sum / 2 - group1 < nums[0]) {
                group1 -= nums[i];
                group2 += nums[i];
                if (group2 == sum / 2) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        System.out.println(canPartition.canPartitionError(new int[] {2,2,4,6,7,8,9,10}));



    }
}
