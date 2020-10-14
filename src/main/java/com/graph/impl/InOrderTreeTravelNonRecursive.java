package com.graph.impl;

import com.graph.TreeTravel;
import com.graph.bean.TreeNode;
import org.springframework.stereotype.Component;

import java.util.Stack;

/**
 * 二叉树的中序遍历-非递归
 */
@Component("inOrderNR")
public class InOrderTreeTravelNonRecursive implements TreeTravel {

    @Override
    public void treeTravel(TreeNode root) {
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
                nodeVisit.visit(node);
                // 进入右子树，开始新一轮到左子树遍历
                node = node.getRight();
            }

        }
    }
}
