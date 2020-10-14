package com.leetcode.weekyRun;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PaintingPlan {

    public int paintingPlan(int n, int k) {
        if (k == n * n || k == 0) return 1;
        if (k < n) return 0;
        if (k == n) return n * 2;
        if (k == n * n - 1) return n * n;
        int i = k / n;
        int j = k % n;
        if (j == 0) {
            return 2 * combain(i, n);
        }
        int count = 1;
        while (i != 0 && ((j / count) != n - i || j % count != 0)) {
            i--;
            count++;
            j += n;
        }
        if (i == 0) return 0;
        if (i == 1) return combain(i, n) * combain(j / count, n);
        return 2 * combain(i, n) * combain(j / count, n);
    }
    private int combain(int m, int n) {
        int re = factorial(n) / factorial(m);
        return re / factorial(n - m);
    }
    private int factorial(int n) {
        int re = 1;
        for (int i = 1; i <= n; i++) {
            re *= i;
        }
        return re;
    }

    public static final int MOD = 1000000007;
    public int keyboard(int k, int n) {
        // 每个键能按 k 次，共按 n 次
        int sum = k * 26;
        long count = 1;
        for (int i = 0; i < n; i++) {
            count *= sum;
            count %= MOD;
            sum--;
        }
        if (n < k) return (int)count / k;
        for (int i = 2; i <= k; i++) {
            if (i <= n) {
                int sub = (combain1(i, n) * 26) * factorial(i);
                count -= sub;
            }
        }
        count %= MOD;
        return (int) count;
    }
    private int combain1(int i, int n) {
        if (i == n) return 1;
        int count = n - i;
        int result = 1;
        for (int i1 = 0; i1 < count; i1++) {
            result *= (n - i1);
        }
        for (int count1 = count; count1 > 0; count1--) {
            result /= count1;
        }
        return result;
    }

//    public int keyboard(int k, int n) {
//        int i = n / k;
//        int j = n % k;
//        for (int i = 1; i <= n; i++) {
//
//        }
//    }

    private static int pre = 0;
    public TreeNode convertBST1(TreeNode root) {
        if (root == null) return root;
        convertBST(root.right);
        root.val += pre;
        pre = root.val;
        convertBST(root.left);
        return root;
    }


    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        int pre = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            while (node.right != null) {
                stack.push(node.right);
                node = node.right;
            }
            do {
                node = stack.pop();
                node.val += pre;
                pre = node.val;
            } while (!stack.isEmpty() && node.left == null);
            if (node.left != null)
                stack.push(node.left);
        }
        return root;
    }

    public int navigation(TreeNode root) {
        int count = 0;
        if (root.left == null || root.right == null) count++;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.right == null && node.left == null) {
                count++;
                continue;
            }
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        int res = count / 2;
        if (count % 2 != 0) res++;
        return res;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int A = (len - i) / 2;
            if (i % 2 == 0) A++;
            int B = i / 2;
            int C = i > 0 ? (i - 1) / 2 : 0;
            if ((i - 1) % 2 == 0) C++;
            int D = i < len - 1 ? (len - i - 1) / 2 + 1 : 0;
            sum += arr[i] * (A + B + C * A + B * D);
        }
        return sum;
    }

    public static void main(String[] args) {
        PaintingPlan p = new PaintingPlan();
//        System.out.println(p.sumOddLengthSubarrays(new int[] {1,4,2,5,3}));
//        System.out.println(p.paintingPlan(5, 23));
//        for (int i = 1; i <= 6; i++) {
//            for (int j = 0; j <= i * i; j++) {
//                System.out.print(p.paintingPlan(i, j)+" ");
//            }
//            System.out.println();
//        }
//        for (int k = 1; k <= 5; k++) {
//            for (int i = 1; i <= 26 * k; i++) {
//                System.out.print(p.keyboard(k, i) + " ");
//            }
//            System.out.println();
//        }

        TreeNode root = new TreeNode(5, new TreeNode(2), new TreeNode(13));
        p.convertBST(root);
    }
}
