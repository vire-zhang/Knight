package com.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HuaWei {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        int count = 0;
        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (top == p) {
                for (TreeNode node : stack) {
                    list1.add(node);
                }
                count++;
            }
            if (top == q) {
                for (TreeNode node : stack) {
                    list2.add(node);
                }
                count++;
            }
            if (count == 2) break;
            if (top.left != null) {
                stack.push(top.left);
                continue;
            }
            if (top.right != null) {
                stack.push(top.right);
                continue;
            }
            TreeNode node = stack.pop();
            while (!stack.isEmpty() && (stack.peek().right == null || stack.peek().right == node))
                node = stack.pop();
            if (!stack.isEmpty())
                stack.push(stack.peek().right);
        }
        int len = Math.min(list1.size(), list2.size());
        for (int i = 0; i < len; i++) {
            if (list1.get(i) != list2.get(i)) return list1.get(i - 1);
        }
        return list1.get(len - 1);
    }
}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
