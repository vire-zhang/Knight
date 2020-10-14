package com.leetcode;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int maxLength = 1;
        int loc = 0;
        for (int i = 1; i < chars.length; i++) {
            if (i > chars.length / 2 && maxLength > (chars.length - i) * 2) break;
            if (chars[i] == chars[i - 1]) {
                int length = 2;
                int left = i - 1;
                int right = i;
                while (left - 1 >= 0 &&
                        right + 1 < chars.length &&
                        chars[left - 1] == chars[right + 1]) {
                    length += 2;
                    --left;
                    ++right;
                }
                if (length > maxLength) {
                    maxLength = length;
                    loc = i;
                }
            }
            if (i != chars.length - 1 && chars[i - 1] == chars[i + 1]) {
                int length = 3;
                int left = i - 1;
                int right = i + 1;
                while (left - 1 >= 0 &&
                        right + 1 < chars.length &&
                        chars[left - 1] == chars[right + 1]) {
                    length += 2;
                    --left;
                    ++right;
                }
                if (length > maxLength) {
                    maxLength = length;
                    loc = i;
                }
            }
        }
        return s.substring(loc - maxLength / 2, loc - maxLength / 2 + maxLength);
    }

    public static void main(String[] args) {
        String s = "ahsgdjsssjakhdk";
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome(s));
    }
}
