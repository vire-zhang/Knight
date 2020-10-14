package com.graph.impl;

import com.graph.TreeTravel;
import com.graph.bean.TreeNode;
import org.springframework.stereotype.Component;

/**
 * 二叉树的中序遍历-递归
 */
@Component("inOrderR")
public class InOrderTreeTravelRecursive implements TreeTravel {

    @Override
    public void treeTravel(TreeNode root) {
        if (root.getLeft() != null)
            treeTravel(root.getLeft());
        nodeVisit.visit(root);
        if (root.getRight() != null)
            treeTravel(root.getRight());
    }
}
