package com.leetcode.weekyRun;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MaxSatisfaction {

    public static int maxSatisfaction(int[] satisfaction) {

        Arrays.sort(satisfaction);
        if (satisfaction[satisfaction.length - 1] <= 0) return 0;
        ArrayList<Integer> fuNum = new ArrayList<Integer>();
        int sum = 0, singleSum = 0;
        int count = 1;
        for (int num : satisfaction) {
            if (num < 0) fuNum.add(num);
            else {
                sum += count * num;
                singleSum += num;
                ++count;
            }
        }
//        int fuCount = 0;
//        if (fuNum != null) {
//            for (int i = 0; i < fuNum.size(); i++) {
//                int plus = (fuCount + 1) * fuNum.get(i) + singleSum;
//                if (plus > 0) {
//                    sum += plus;
//                    ++fuCount;
//                }
//            }
//        }
        if (fuNum != null) {
            for (int i = fuNum.size() - 1; i >= 0; i--) {
                int plus = fuNum.get(i) + singleSum;
                if (plus > 0) {
                    sum += plus;
                    singleSum += fuNum.get(i);
                }
            }
        }
        return  sum;
    }

    public static void main(String[] args) {
        int[] nums = {-5,-7,8,-2,1,3,9,5,-10,4,-5,-2,-1,-6};
        System.out.println(MaxSatisfaction.maxSatisfaction(nums));
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            sum += (i) * nums[i];
        }
        System.out.println(sum);
    }
}
