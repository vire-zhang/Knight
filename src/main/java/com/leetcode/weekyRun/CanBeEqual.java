package com.leetcode.weekyRun;

import java.util.*;

public class CanBeEqual {
    public boolean canBeEqual(int[] target, int[] arr) {
        if (target.length != arr.length) return false;
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }

    public boolean hasAllCodes(String s, int k) {
        for (int i = 0; i < Math.pow(2, k); i++) {
            StringBuilder binary = new StringBuilder(Integer.toBinaryString(i));
            for (int j = binary.length(); j < k; j++) {
                binary.insert(0, '0');
            }
            if (!s.contains(binary)) {
                return false;
            }
        }
        return true;
    }

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> pre = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            Set<Integer> temp = new HashSet<>();
            temp.add(prerequisite[1]);
            if (pre.containsKey(prerequisite[0])) {
                pre.get(prerequisite[0]).addAll(temp);
            } else pre.put(prerequisite[0], temp);
        }
        pre.forEach((k, v) -> {
            Stack<Integer> stack = new Stack<>();
            Set<Integer> set = new HashSet<>();
            for (Integer i : v) {
                stack.push(i);
                set.add(i);
            }
            while (!stack.empty()) {
                Integer i = stack.pop();
                if (pre.containsKey(i)) {
                    v.addAll(pre.get(i));
                    for (Integer j : pre.get(i)) {
                        if (set.add(j)) stack.push(j);
                    }
                }
            }
        });
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            if (pre.containsKey(query[0])) {
                if (pre.get(query[0]).contains(query[1])) result.add(true);
                else result.add(false);
            }
            else result.add(false);
        }
        return result;
    }

    public static void main(String[] args) {
        CanBeEqual canBeEqual = new CanBeEqual();
//        System.out.println(canBeEqual.canBeEqual(new int[]{1,2,3,4}, new int[]{2,4,1,3}));
//        System.out.println(canBeEqual.hasAllCodes("00110110", 2));
        System.out.println(canBeEqual.checkIfPrerequisite(3, new int[][] {{1,2},{1,0},{2,0}}, new int[][] {{1,0},{1,2}}));
    }
}
