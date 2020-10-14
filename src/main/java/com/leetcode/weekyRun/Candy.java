package com.leetcode.weekyRun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Candy {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int candy : candies) {
            if (candy > max) max = candy;
        }

        List<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max)
                result.add(true);
            else
                result.add(false);
        }
        return result;
    }

    public int maxDiff(int num) {
        char[] digit = Integer.toString(num).toCharArray();
        char[] digit2 = Integer.toString(num).toCharArray();
        return change(digit, '9') - change(digit2, '1');
    }

    private int change (char[] digit, char target) {
        char c = digit[0];
        int j = 1;
        if (target == '9') {
            while (c == target && j < digit.length) {
                c = digit[j];
                ++j;
            }
        } else {
            while (c <= target && j < digit.length) {
                c = digit[j];
                ++j;
            }
        }
        if (j != 1 && target == '1') target = '0';
        for (int i = j - 1; i < digit.length; i++) {
            if (digit[i] == c) digit[i] = target;
        }

        return Integer.parseInt(String.valueOf(digit));
    }

    public boolean checkIfCanBreak(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        int i = 0;
        while (i < s1.length() && chars1[i] == chars2[i])
            ++i;
        if (i == s1.length()) return true;
        boolean flag = chars1[i] > chars2[i] ? true : false;
        while (i < s1.length()) {
            boolean compare = chars1[i] > chars2[i];
            if (chars1[i] == chars2[i]) compare = flag;
            if (flag != compare) return false;
            ++i;
        }
        return true;
    }

    public static void main(String[] args) {
        Candy candy = new Candy();
//        System.out.println(candy.maxDiff(12345));
//        System.out.println(candy.checkIfCanBreak("pazunsabwlseseeiimsmftchpafqkquovuxhhkpvphw",
//                "nkrtxuiuhbcyqulfqyzgjjwjrlfwwxotcdtqsmfeing"));
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,3,5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2,3,6));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(4,7));
        List<List<Integer>> hats = new ArrayList<>();
        hats.add(list1);
        hats.add(list2);
        hats.add(list3);
        System.out.println(candy.numberWays(hats));
    }

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        final int MOD = 1000000007;
        int[] dp = new int[1 << n];
        dp[0] = 1;
        for (int hat = 1; hat <= 40; hat++) {
            int[] dp2 = new int[1 << n];
            dp2 = dp.clone();
            ArrayList<Integer> people = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (Integer h : hats.get(i)) {
                    if (h == hat) {
                        people.add(i);
                        break;
                    }
                }
            }
            for (int mask = 0; mask < (1 << n); mask++) {
                for (Integer person : people) {
                    if (((1 << person) & mask) == 0) {
                        int state = ((1 << person) | mask);
                        dp2[state] += dp[mask];
                        if (dp2[state] >= MOD) dp2[state] -= MOD;
                    }
                }
            }
            dp = dp2.clone();
        }
        return dp[(1 << n) - 1];
    }
}
