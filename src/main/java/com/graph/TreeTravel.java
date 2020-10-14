package com.graph;

import com.graph.bean.TreeNode;
import com.graph.util.NodeVisit;

/**
 * 二叉树的遍历
 */
public interface TreeTravel {

    static final NodeVisit nodeVisit = new NodeVisit();

    void treeTravel(TreeNode root);
}
