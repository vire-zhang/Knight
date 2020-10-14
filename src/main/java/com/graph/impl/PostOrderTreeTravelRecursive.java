package com.graph.impl;

import com.graph.TreeTravel;
import com.graph.bean.TreeNode;
import org.springframework.stereotype.Component;

/**
 * 二叉树的后序遍历-递归
 */
@Component("postOrderR")
public class PostOrderTreeTravelRecursive implements TreeTravel {

    @Override
    public void treeTravel(TreeNode root) {
        if (root.getLeft() != null)
            treeTravel(root.getLeft());
        if (root.getRight() != null)
            treeTravel(root.getRight());
        nodeVisit.visit(root);
    }
}
