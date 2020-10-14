package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CountSmaller {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return result;
        result.add(0);

        ArrayList<Integer> sortArr = new ArrayList<Integer>();
        sortArr.add(nums[nums.length - 1]);
        int i = nums.length - 2;
        int count = 0;
        while (i >= 0) {
            count =  findLoc(sortArr, nums[i]);
            result.add(0, count);
            sortArr.add(count, nums[i]);
            --i;
        }
        return result;
    }

    private  int findLoc(ArrayList<Integer> nums, int target) {
        if (nums.size() == 0) return 0;
        int beg = 0, end = nums.size() - 1;
        int mid = (end + beg) / 2;
        while (true) {
            if (nums.get(mid) < target) {
                if (mid + 1 == nums.size() || nums.get(mid + 1) >= target)
                    return mid + 1;
                beg = mid + 1;
            } else {
                if (mid - 1 == -1 || nums.get(mid - 1) < target)
                    return mid;
                end = mid - 1;
            }
            mid = (end + beg) / 2;
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        CountSmaller countSmaller = new CountSmaller();
        for (Integer c : countSmaller.countSmaller(nums)) {
            System.out.println(c);
        }
    }
}
