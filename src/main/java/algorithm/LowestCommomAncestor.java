package algorithm;

public class LowestCommomAncestor {

    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // base case
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 情况 1
        if (left != null && right != null) return root;
        // 情况 2
        if (left == null && right == null) return null;
        // 情况 3
        return left == null ? right : left;
    }
}

class TreeNode {

    /**
     * 节点值
     */
    int val;

    /**
     * 左子树
     */
    TreeNode left;

    /**
     * 右子树
     */
    TreeNode right;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
