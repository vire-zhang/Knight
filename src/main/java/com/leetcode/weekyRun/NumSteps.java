package com.leetcode.weekyRun;

public class NumSteps {

    public static int numSteps(String s) {
        StringBuilder sb = new StringBuilder(s);
        char[] chars = s.toCharArray();
        int count = 0;
        while (!sb.toString().equals("1")) {
            if (sb.charAt(sb.length() - 1) == '1') {
                ++count;
                sb.setCharAt(sb.length() - 1, '0');
                int i = sb.length() - 2;
                while (i >= 0 && sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                    --i;
                }
                if (i == -1) sb.insert(0, "1");
                else sb.setCharAt(i, '1');
            }
            else {
                sb.deleteCharAt(sb.length() - 1);
                ++count;
            }
        }
        return  count;
    }

    public static void main(String[] args) {
        System.out.println(NumSteps.numSteps("1101"));
    }

}
