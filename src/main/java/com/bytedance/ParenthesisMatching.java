package com.bytedance;

import java.util.*;

public class ParenthesisMatching {

    /**
     * 括号匹配
     * @param s
     * @return
     */
    public static boolean isMatching(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (Arrays.asList('{', '[', '(').contains(c))
                stack.push(c);
            else if (stack.isEmpty()) return false;
            else if (!map.get(stack.pop()).equals(c)) return false;
        }
        if (!stack.isEmpty()) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(ParenthesisMatching.threeSumClosest(new int[]{0,2,1,-3}, 1));
    }

    /**
     * 最小公倍数 = (a * b) / 最大公约数
     * @param a
     * @param b
     * @return
     */
    public int LeastCommonMultiple(int a, int b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }

    /**
     * 最大公约数
     * @param a
     * @param b
     * @return
     */
    private int greatestCommonDivisor(int a, int b) {
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }
        while (b != 0) {
            if (a == b) return a;
            int k = a % b;
            a = b;
            b = k;
        }
        return a;
    }

    public static int threeSumClosest(int[] nums, int target) {
        int cloest = nums[0] + nums[1] +nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = first + nums[left] + nums[right];
                if (Math.abs(cloest - target) > Math.abs(sum - target)) cloest = sum;
                if (sum > target) right--;
                if (sum < target) left++;
                if (cloest == target) return cloest;
            }
        }
        return cloest;
    }
}
