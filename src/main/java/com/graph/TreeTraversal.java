package com.graph;

import com.graph.bean.TreeNode;
import com.sun.source.tree.Tree;

import java.util.Stack;

public class TreeTraversal {

    public void visit(TreeNode node) {
        if (node != null) {
            System.out.println(node.getVal());
        }
    }

    // 先序遍历-递归
    public void preOrderRecursive(TreeNode root) {
        // 访问根结点
        visit(root);
        // 遍历左子树
        if (root.getLeft() != null)
            preOrderRecursive(root.getLeft());
        // 遍历右子树
        if (root.getRight() != null)
            preOrderRecursive(root.getRight());
    }

    // 先序遍历-非递归
    public void preOrderNonRecursive(TreeNode root) {

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.empty()) {
            TreeNode node = nodeStack.pop();
            visit(node);
            if (node.getRight() != null) {
                nodeStack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                nodeStack.push(node.getLeft());
            }
        }
    }

    // 中序遍历-递归
    public void inOrderRecursive(TreeNode root) {
        if (root.getLeft() != null)
            inOrderRecursive(root.getLeft());
        visit(root);
        if (root.getRight() != null)
            inOrderRecursive(root.getRight());
    }

    // 中序遍历-非递归
    public void inOrderNonRecursive(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode node = root;
        while (!nodeStack.empty() || node != null) {

            // 循环入栈到左子树最下边到叶子结点
            while (node != null) {
                nodeStack.push(node);
                node = node.getLeft();
            }

            // 出栈
            if (!nodeStack.empty()) {
                node = nodeStack.pop();
                visit(node);
                // 进入右子树，开始新一轮到左子树遍历
                node = node.getRight();
            }

        }
    }

    // 后序遍历-递归
    public void postOrderRecursive(TreeNode root) {
        if (root.getLeft() != null)
            postOrderRecursive(root.getLeft());
        if (root.getRight() != null)
            postOrderRecursive(root.getRight());
        visit(root);
    }

    // 后序遍历-非递归
    public void postOrderNonRecursive(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        // 当前节点
        TreeNode currNode = root;
        // 上次访问节点
        TreeNode lastVistNode = null;
        while (!nodeStack.empty() || currNode != null) {

            // 循环入栈到左子树最下边到叶子结点
            while (currNode != null) {
                nodeStack.push(currNode);
                currNode = currNode.getLeft();
            }

            // 出栈
            if (!nodeStack.empty()) {
                currNode = nodeStack.peek();
                if (currNode.getRight() == null || currNode.getRight() == lastVistNode) {
                    nodeStack.pop();
                    // 当右子树为空或右子树已被访问过，才访问当前节点
                    visit(currNode);
                    lastVistNode = currNode;
                    currNode = null;
                }
                else {
                    currNode = currNode.getRight();
                }
            }

        }
    }
}
