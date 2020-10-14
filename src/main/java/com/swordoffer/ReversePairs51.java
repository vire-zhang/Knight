package com.swordoffer;

import java.util.*;

public class ReversePairs51 {

    int step = 0;

    public int reversePairs(int[] nums) {
        int count = 0;
        ArrayList<Integer> ns = new ArrayList<Integer>();
        for (int num : nums) {
            ++step;
            int i = findLoc(ns, num);
            count += ns.size() - i;
            ns.add(i, num);
        }
        return count;
    }

    private  int findLoc(ArrayList<Integer> nums, int target) {
        if (nums.size() == 0) return 0;
        int beg = 0, end = nums.size() - 1;
        int mid = (end + beg) / 2;
        while (true) {
            ++step;
            if (nums.get(mid) <= target) {
                if (mid + 1 == nums.size() || nums.get(mid + 1) > target)
                    return mid + 1;
                beg = mid + 1;
            } else {
                if (mid - 1 == -1 || nums.get(mid - 1) <= target)
                    return mid;
                end = mid - 1;
            }
            mid = (end + beg) / 2;
        }
    }

    public static void main(String[] args) {

//        int[] nums = {6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5};
//        int[] nums = {6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9};
        int[] nums = {6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,6,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3,4,5,5,9,7,10,1,2,3};
        System.out.println(nums.length);
        long beg = System.nanoTime();
        ReversePairs51 reversePairs51 = new ReversePairs51();
        System.out.println(reversePairs51.reversePairs(nums));
        long end = System.nanoTime();
        System.out.println(reversePairs51.step);
        System.out.println(end - beg);

        System.out.println();
        long beg2 = System.nanoTime();
        ReversePairs51MergeSort sort = new ReversePairs51MergeSort();
        System.out.println(sort.reversePairs(nums));
        long end2 = System.nanoTime();
        System.out.println(sort.step);
        System.out.println(end2 - beg2);
    }
}
