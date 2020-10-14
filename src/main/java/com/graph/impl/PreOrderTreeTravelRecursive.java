package com.graph.impl;

import com.graph.TreeTravel;
import com.graph.bean.TreeNode;
import org.springframework.stereotype.Component;

/**
 * 二叉树的先序遍历-递归
 */
@Component("preOrderR")
public class PreOrderTreeTravelRecursive implements TreeTravel {

    @Override
    public void treeTravel(TreeNode root) {
        // 访问根结点
        nodeVisit.visit(root);
        // 遍历左子树
        if (root.getLeft() != null)
            treeTravel(root.getLeft());
        // 遍历右子树
        if (root.getRight() != null)
            treeTravel(root.getRight());
    }
}
