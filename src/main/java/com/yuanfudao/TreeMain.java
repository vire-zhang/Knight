package com.yuanfudao;

public class TreeMain {

    public static void main(String[] args) {
        TreeNode aTree = new TreeNode(1);
        TreeNode treeLeft = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode treeRight = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        aTree.setLeft(treeLeft);
        aTree.setRight(treeRight);

        BST bst = new BST();
        bst.isValidBST(aTree);
    }

    private TreeNode creatTree() {
        TreeNode aTree = new TreeNode(1);
        TreeNode treeLeft = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode treeRight = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        aTree.setLeft(treeLeft);
        aTree.setRight(treeRight);
        return aTree;
    }
}
