package com.leetcode;

public class Daily {

    public static void sortColors1(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int mid = 0;
        while (i < j) {
            while (i < nums.length && nums[i] == 0) i++;
            while (j >= 0 && nums[j] == 2) j--;
            if (i == nums.length || j < 0 || i >= j) break;
            if (nums[i] == 2) {
                nums[i] = nums[j];
                nums[j] = 2;
                j--;
                continue;
            }
            if (nums[j] == 0) {
                nums[j] = nums[i];
                nums[i] = 0;
                i++;
                continue;
            }
            if (nums[i] == 1 && mid == 0) mid = i;
            while (mid < nums.length && nums[mid] == 1) mid++;
            if (mid >= j) break;
            if (nums[mid] == 0) {
                nums[i] = 0;
                nums[mid] = 1;
                i++;
            }
            if (nums[mid] == 2) {
                nums[j] = 2;
                nums[mid] = 1;
                j--;
            }
        }
    }

    public static void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int k = 0;
        while (k <= j) {
            if (nums[k] == 0) {
                if (nums[i] != 1) {
                    nums[k] = nums[i];
                }
                nums[i] = 0;
                i++;
            }
            if (nums[k] == 2) {
                nums[j] = 2;
                j--;
            }
            k++;
        }
        while (i <= j) nums[i] = 1;
    }

    public static void main(String[] args) {
        Daily.sortColors(new int[]{2,0,2,1,1,0});
    }
}
