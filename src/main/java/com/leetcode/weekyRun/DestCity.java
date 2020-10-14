package com.leetcode.weekyRun;

import java.util.*;

public class DestCity {
    public String destCity(List<List<String>> paths) {
        Map<String, String> travel = new HashMap<>();
        for (List<String> path : paths) {
            travel.put(path.get(0), path.get(1));
        }
        for (List<String> path : paths) {
            if (travel.get(path.get(1)) == null) return path.get(1);
        }
        return "";
    }

    public boolean kLengthApart(int[] nums, int k) {
        int count = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num == 1) {
                if (count != Integer.MIN_VALUE && count < k) return false;
                count = 0;
            } else {
                if (count != Integer.MIN_VALUE) ++count;
            }
        }
        return true;
    }

    public int longestSubarray(int[] nums, int limit) {
        int beg = 0, end = 0;
        int min = nums[0], max = nums[0];
        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= max - limit && nums[i] <= min + limit) {
                end = i;
                if (nums[i] > max) max = nums[i];
                if (nums[i] < min) min = nums[i];
            }
            else {
                int tempBeg = end;
                max = nums[i];
                min = nums[i];
                while (Math.abs(nums[tempBeg] - nums[i]) <= limit) {
                    if (nums[tempBeg] > max) max = nums[tempBeg];
                    if (nums[tempBeg] < min) min = nums[tempBeg];
                    --tempBeg;
                }
                if (end - beg + 1 > maxLength) maxLength = end - beg + 1;
                beg = tempBeg + 1;
                end = i;
            }
        }
        if (end - beg + 1 > maxLength) maxLength = end - beg + 1;
        return maxLength;
    }

    public int kthSmallest(int[][] mat, int k) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            if (i == 0) {
                for (int i1 : mat[i]) curr.add(i1);
                if (curr.size() > k) curr = curr.subList(0, k);
            } else {
                pre = curr;
                curr = new ArrayList<Integer>();
                for (Integer p : pre) {
                    for (int m : mat[i]) {
                        curr.add(p + m);
                    }
                }
                Collections.sort(curr);
                if (curr.size() > k) curr = curr.subList(0, k);
            }
        }
        return curr.get(k - 1);
    }

    public static void main(String[] args) {
        DestCity destCity = new DestCity();
        int[] nums = {8,2,4,7};
        System.out.println(destCity.longestSubarray(nums, 4));
    }
}
