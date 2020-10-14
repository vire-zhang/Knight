package com.graph.util;

import com.graph.bean.TreeNode;

public class NodeVisit {

    public NodeVisit() {
        super();
    }

    public void visit(TreeNode node) {
        if (node != null) {
            System.out.println(node.getVal());
        }
    }
}
