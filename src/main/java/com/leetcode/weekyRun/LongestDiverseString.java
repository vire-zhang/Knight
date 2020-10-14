package com.leetcode.weekyRun;

public class LongestDiverseString {

    public static String longestDiverseString(int a, int b, int c) {

        // 排序
        int max, second, third;
        char maxC, secondC, thirdC;
        if (a >= b) {
            max = a;
            maxC = 'a';
            second = b;
            secondC = 'b';
        } else {
            max = b;
            maxC = 'b';
            second = a;
            secondC = 'a';
        }
        third = c;
        thirdC = 'c';
        if (c > max) {
            third = second;
            thirdC = secondC;
            second = max;
            secondC = maxC;
            max = c;
            maxC = 'c';
        } else if (c > second) {
            third = second;
            thirdC = secondC;
            second = c;
            secondC = 'c';
        }
        int minSpace = (max + 1) / 2 - 1;
        int maxGroup = second + third;
        StringBuilder result = new StringBuilder("");
        if (maxGroup < minSpace) {
            // 舍弃最长串
            result.insert(0, maxC);
            result.insert(0, maxC);
            while (second != 0 || third != 0) {
                if (second != 0) {
                    result.insert(result.length(), secondC);
                    --second;
                } else if (third != 0) {
                    result.insert(result.length(), thirdC);
                    --third;
                }
                result.insert(result.length(), maxC);
                result.insert(result.length(), maxC);
            }
        } else {
            // 所有字母均可放入
            for (int i = 0; i < max; i++) {
                result.insert(0, maxC);
            }
            int loc = result.length() - 2;
            for (int i = 0; i < minSpace; i++) {
                if (second != 0) {
                    result.insert(loc, secondC);
                    --second;
                } else {
                    result.insert(loc, thirdC);
                    --third;
                }
                loc -= 2;
            }
            loc = result.length();
            while (second != 0 || third != 0) {
                if (second != 0) {
                    result.insert(loc, secondC);
                    --second;
                } else {
                    result.insert(loc, thirdC);
                    --third;
                }
                loc -= 2;
                if (loc < 0) loc = result.length();
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(LongestDiverseString.longestDiverseString(1,8,12));
    }
}
