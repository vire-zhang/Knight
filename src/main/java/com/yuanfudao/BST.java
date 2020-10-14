package com.yuanfudao;

import java.util.ArrayList;
import java.util.Stack;

public class BST {

//    public boolean isValidBST(TreeNode root) {
//        boolean result = true;
//        ArrayList<Integer> treeVal = inOrderVisit(root);
//        if (treeVal.size() > 1) {
//            for (int i = 1; i < treeVal.size(); i++) {
//                if (treeVal.get(i) <= treeVal.get(i - 1)) {
//                    result = false;
//                    break;
//                }
//            }
//        }
//        return result;
//    }
//
//    private ArrayList<Integer> inOrderVisit(TreeNode root) {
//        ArrayList<Integer> result = new ArrayList<>();
//        if (root == null) return result;
//        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
//        TreeNode node = root;
//        while (!nodeStack.empty() || node != null) {
//            while (node != null) {
//                nodeStack.push(node);
//                node = node.left;
//            }
//            if (nodeStack != null) {
//                node = nodeStack.pop();
//                result.add(node.val);
//                node = node.right;
//            }
//        }
//        return result;
//    }
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        double preVal = -Double.MAX_VALUE;
        while (!nodeStack.empty() || node != null) {
            while (node != null) {
                nodeStack.push(node);
                node = node.left;
            }
            if (nodeStack != null) {
                node = nodeStack.pop();
                if (node.val <= preVal) return false;
                preVal = node.val;
                node = node.right;
            }
        }
        return true;
    }
}
