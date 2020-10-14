package com.graph.impl;

import com.graph.TreeTravel;
import com.graph.bean.TreeNode;
import org.springframework.stereotype.Component;

import java.util.Stack;

/**
 * 二叉树的后序遍历-非递归
 */
@Component("postOrderNR")
public class PostOrderTreeTravelNonRecursive implements TreeTravel {

    @Override
    public void treeTravel(TreeNode root) {
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
                    nodeVisit.visit(currNode);
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
