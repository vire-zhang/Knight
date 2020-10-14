package com.swordoffer;

public class IsMatch19 {

    public static boolean isMatch(String s, String p) {

        char[] source = s.toCharArray();
        char[] pattern = p.toCharArray();
        if (p.equals(".*")) return true;
        if (s.equals("")) {
            if (p.equals("") || (p.length() == 2 && pattern[1] == '*'))
                return true;
            else return false;
        }

        int i = 0, j = 0;
        StringBuilder ss = new StringBuilder(s);
        StringBuilder pp = new StringBuilder(p);
        int delete = 0;
        while (i < source.length && j < pattern.length) {
            if (pattern[j] == '.') {
                ++i;
                ++j;
            }
            else if (pattern[j] == '*') ++j;
            else if (j < p.length() - 1 && pattern[j + 1] =='*') j += 2;
            else {
                while (i < s.length() && source[i] != pattern[j]) {
                    ++i;
                }
                if (i == s.length()) return false;
                ss.deleteCharAt(i - delete);
                pp.deleteCharAt(j - delete);
                ++i;
                ++j;
                ++delete;
            }
        }

        source = ss.toString().toCharArray();
        pattern = pp.toString().toCharArray();
        i = 0;
        j = 0;
        while (i < source.length && j < pattern.length) {
            if (source[i] == pattern[j] || pattern[j] == '.') {
                ++i;
                ++j;
            }
            else {
                if (pattern[j] == '*') {
                    if (j == 0) return false;
                    char temp = source[i - 1];
                    while (i != source.length && source[i] == temp) ++i;
                    ++j;
                    while (j != pattern.length && pattern[j] == temp) ++j;
                } else {
                    if (j == pattern.length - 1) return false;
                    if (pattern[j + 1] == '*')
                        j += 2;
                    else return false;
                }
            }
        }
        if (i < source.length) return false;
        if (j < pattern.length) {
            if (pattern[j] == '*') ++j;
            while (j < pattern.length - 1) {
                if (pattern[j + 1] == '*') j += 2;
                else return false;
            }
            if (j != pattern.length) return false;
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(IsMatch19.isMatch("aaca", "ab*a*c*a"));
    }
}
