package com.graph.impl;

import com.graph.TreeTravel;
import com.graph.bean.TreeNode;
import org.springframework.stereotype.Component;

import java.util.Stack;

/**
 * 二叉树的先序遍历-非递归
 */
@Component("preOrderNR")
public class PreOrderTreeTravelNonRecursive implements TreeTravel {

    @Override
    public void treeTravel(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.empty()) {
            TreeNode node = nodeStack.pop();
            nodeVisit.visit(node);
            if (node.getRight() != null) {
                nodeStack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                nodeStack.push(node.getLeft());
            }
        }
    }
}
