package com.leetcode.weekyRun;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Double31 {

    private final int MOD = 1000000007;
    public int numOfSubarrays(int[] arr) {
        int count = 0;
        int odd = 0;
        int even = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                even++;
            }
            if (arr[i] % 2 == 1) {
                int temp = odd;
                odd = even + 1;
                even = temp;
            }
            count = (count + odd) % MOD;
        }
        return count;
    }

    public int numSplits(String s) {
        Map<Character, Integer> right = new HashMap<>();
        for (char c : s.toCharArray()) {
            right.merge(c, 1, Integer::sum);
        }
        Map<Character, Integer> left = new HashMap<>();
        int count = 0;
        for (char c : s.toCharArray()) {
            left.merge(c, 1, Integer::sum);
            if(right.containsKey(c)) {
                if (right.get(c) == 1) right.remove(c);
                else right.replace(c, right.get(c) - 1);
            }
            if (left.size() == right.size()) count++;
        }
        return count;
    }

    public int minNumberOperations(int[] target) {
        int count = 1;
        int begin = -1;
        for (int i = 0; i < target.length; i++) {
            target[i]--;
            if (begin == -1 && target[i] != 0) begin = i;
            if (begin != -1) {
                if (target[i] == 0) {
                    count += minNumberOperations(Arrays.copyOfRange(target, begin, i));
                    begin = -1;
                }
                else if (i == target.length - 1) {
                    count += minNumberOperations(Arrays.copyOfRange(target, begin, i + 1));
                }
            }
        }
        return count;
    }

    public int minNumberOperations2(int[] target) {
        int count = target[0];
        for (int i = 1; i < target.length; i++) {
            count += Math.max(target[i] - target[i - 1], 0);
        }
        return count;
    }
}
