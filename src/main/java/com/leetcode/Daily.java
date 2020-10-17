package com.leetcode;

import java.util.*;

public class Daily {

    public static void sortColors1(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int mid = 0;
        while (i < j) {
            while (i < nums.length && nums[i] == 0) i++;
            while (j >= 0 && nums[j] == 2) j--;
            if (i == nums.length || j < 0 || i >= j) break;
            if (nums[i] == 2) {
                nums[i] = nums[j];
                nums[j] = 2;
                j--;
                continue;
            }
            if (nums[j] == 0) {
                nums[j] = nums[i];
                nums[i] = 0;
                i++;
                continue;
            }
            if (nums[i] == 1 && mid == 0) mid = i;
            while (mid < nums.length && nums[mid] == 1) mid++;
            if (mid >= j) break;
            if (nums[mid] == 0) {
                nums[i] = 0;
                nums[mid] = 1;
                i++;
            }
            if (nums[mid] == 2) {
                nums[j] = 2;
                nums[mid] = 1;
                j--;
            }
        }
    }

    public static void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int k = 0;
        while (k <= j) {
            if (nums[k] == 0) {
                if (nums[i] != 1) {
                    nums[k] = nums[i];
                }
                nums[i] = 0;
                i++;
            }
            if (nums[k] == 2) {
                nums[j] = 2;
                j--;
            }
            k++;
        }
        while (i <= j) nums[i] = 1;
    }

    public static void main(String[] args) {
        Daily.sortColors(new int[]{2,0,2,1,1,0});
    }

    public List<String> commonChars(String[] A) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < A[0].length(); i++) {
            map.merge(A[0].charAt(i), 1, Integer::sum);
        }
        for (int i = 1; i < A.length; i++) {
            String s = A[i];
            Map<Character, Integer> tmp = new HashMap<>();
            for (int j = 0; j < s.length(); j++) {
                if (map.containsKey(s.charAt(j)) && map.get(s.charAt(j)) != 0) {
                    tmp.merge(s.charAt(j), 1, Integer::sum);
                    map.replace(s.charAt(j), map.get(s.charAt(j)) - 1);
                }
            }
            map = tmp;
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            for (Integer i = 0; i < entry.getValue(); i++) {
                result.add(entry.getKey().toString());
            }
        }
        return result;
    }

    public int[] sortedSquares(int[] A) {
        int i = 0;
        int j = A.length - 1;
        int[] result = new int[A.length];
        int k = j;
        while (i != j) {
            int a = (int)Math.pow(A[i], 2);
            int b = (int)Math.pow(A[j], 2);
            if (a > b) {
                result[k] = a;
                i++;
            } else {
                result[k] = b;
                j--;
            }
            k--;
        }
        result[k] = (int)Math.pow(A[i], 2);
        return result;
    }

    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        int i = 0;
        while (!queue.isEmpty()) {
            int count = (int)Math.pow(2, i);
            Node next = null;
            while (count != 0) {
                Node node = queue.poll();
                node.next = next;
                next = node;
                if (node.right != null) {
                    queue.add(node.right);
                    queue.add(node.left);
                }
                count--;
            }
            i++;
        }
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
