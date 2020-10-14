package com.leetcode.impl;

import com.graph.bean.TreeNode;
import com.leetcode.BinaryTreeMaxPathSum;

public class MaxPathSumRecursiveImpl implements BinaryTreeMaxPathSum {

    private int max_sum = Integer.MIN_VALUE;

    @Override
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return this.max_sum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) return 0;

        // max_gain from left node
        int left_gain = Math.max(maxGain(node.getLeft()), 0);

        // max_gain from right node
        int right_gain = Math.max(maxGain(node.getRight()), 0);

        // price_newPath include current node
        int price_newPath = left_gain + right_gain + node.getVal();

        // update max_sum
        if (price_newPath > this.max_sum) {
            this.max_sum = price_newPath;
        }

        return Math.max(left_gain, right_gain) + node.getVal();
    }
}
