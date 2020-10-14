package com.leetcode.weekyRun;

import java.util.*;

public class CountLargestGroup {

    public static int countLargestGroup(int n) {

        Map<Integer, Integer> groups = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int sumBit = 0;
            int num = i;
            while (num != 0) {
                sumBit += num % 10;
                num /= 10;
            }
            if (groups.get(sumBit) == null) groups.put(sumBit, 1);
            else {
                groups.replace(sumBit,groups.get(sumBit) + 1);
            }
        }
        int groupCount = 0, maxCount = 0;
        Collection<Integer> counts = groups.values();
        for (Integer count : counts) {
            if (maxCount == 0 || count > maxCount) {
                maxCount = count;
                groupCount = 1;
            } else if (count == maxCount) {
                ++groupCount;
            }
        }
        return groupCount;
    }

    public static void main(String[] args) {
        System.out.println(countLargestGroup(24));
    }
}
