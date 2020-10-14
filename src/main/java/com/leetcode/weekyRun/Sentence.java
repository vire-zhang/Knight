package com.leetcode.weekyRun;

import java.awt.*;
import java.util.*;

public class Sentence {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] strs = sentence.split(" ");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].startsWith(searchWord))
                return i + 1;
        }
        return -1;
    }

    public int maxVowels(String s, int k) {
        int count = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > k - 1) {
                max = count > max ? count : max;
                count = isVowel(s.charAt(i - k)) ? count - 1 : count;
            }
            if (isVowel(s.charAt(i))) ++count;
            if (i >= k - 1 && count == k) return k;
        }
        max = count > max ? count : max;
        return max;
    }

    private boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'u' || c == 'o')
            return true;
        return false;
    }

    public int pseudoPalindromicPaths (TreeNode root) {
        int count = 0;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        Stack<ArrayList<Integer>> paths = new Stack<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(root.val);
        paths.push(temp);

        int[] nodeCnt;

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            ArrayList<Integer> path = paths.pop();
            boolean isLeaf = true;
            if (node.right != null) {
                stack.push(node.right);
                ArrayList<Integer> pathRight = (ArrayList<Integer>) path.clone();
                pathRight.add(node.right.val);
                paths.push(pathRight);
                isLeaf = false;
            }
            if (node.left != null) {
                stack.push(node.left);
                ArrayList<Integer> pathLeft = (ArrayList<Integer>) path.clone();
                pathLeft.add(node.left.val);
                paths.push(pathLeft);
                isLeaf = false;
            }
            if (isLeaf) {
                nodeCnt = new int[9];
                for (Integer i : path) {
                    ++nodeCnt[i - 1];
                }
                int oddCnt = 0;
                for (int j = 1; j <= 9; j++) {
                    if (nodeCnt[j - 1] % 2 == 1) ++oddCnt;
                }
                if (oddCnt <= 1) ++count;
            }
        }

        return count;
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int[][] maxDP = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                maxDP[i][j] = nums1[i] * nums2[j];
                if (i > 0 && maxDP[i - 1][j] > maxDP[i][j]) maxDP[i][j] = maxDP[i - 1][j];
                if (j > 0 && maxDP[i][j - 1] > maxDP[i][j]) maxDP[i][j] = maxDP[i][j - 1];
                if (i > 0 && j > 0) maxDP[i][j] = Math.max(maxDP[i - 1][j - 1] + nums1[i] * nums2[j], maxDP[i][j]);
            }
        }
        return maxDP[nums1.length - 1][nums2.length - 1];
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(3, new TreeNode(3), new TreeNode(1)), new TreeNode(1, null, new TreeNode(1)));
//        TreeNode root = new TreeNode(1, new TreeNode(9, null, new TreeNode(1)), new TreeNode(1, null, new TreeNode(1, new TreeNode(7, null, new TreeNode(4)), null)));
        Sentence s = new Sentence();
        System.out.println(s.pseudoPalindromicPaths(root));
        System.out.println(0 % 2);
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queue1 = new ArrayDeque<>();

        var staff = new LinkedList<String>();
        staff.add("A");
        staff.add("B");
        staff.add("C");
        System.out.println(staff);
        ListIterator<String> iter1 = staff.listIterator();
        iter1.next();
        iter1.add("J");
        System.out.println(staff);
        iter1.previous();
        iter1.add("O");
        System.out.println(staff);
        iter1.next();
        iter1.remove();
        iter1.previous();
        iter1.remove();
        iter1.next();
        iter1.set("P");
        System.out.println(staff);

        var hashSet = new HashSet<String>(staff);
        System.out.println(staff.get(0).hashCode());
        System.out.println("A".hashCode());
//        Rectangle
        TreeSet treeSet = new TreeSet<String>();
        treeSet.add("Bob");
        treeSet.add("Alice");
        treeSet.add("Amy");
        treeSet.add("Carl");
        treeSet.add("Alex");
        treeSet.add("Jack");
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
    }
}
