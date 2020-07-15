package com.honey.leetcode.solution.problem;


import com.honey.leetcode.model.TreeNode;

/**
 * @author hualin.su
 * @date 2020-04-15 22:48
 */
public class Problem99RecoverTree {

    public static void main(String[] args) {

    }

    public void recoverTree(TreeNode root) {
        recoverTree(root, null);
    }

    private void recoverTree(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null && left.val > root.val) {

        }
    }

    private void swapTreeNode(TreeNode parent, TreeNode root, TreeNode left, TreeNode right) {
        if (left != null) {
            TreeNode tmp = root;
            root.left = left.left;
            root.right = right.right;
            if (parent.val > root.val) {
                parent.right = left;
            } else {
                parent.left = left;
            }
            left.left = root;
            left.right = tmp.right;
        }
        if (right != null) {
            TreeNode tmp = root;
            root.left = right.left;
            root.right = right.right;
            if (parent.val > root.val) {
                parent.right = right;
            } else {
                parent.left = right;
            }
            left.left = root;
            left.right = tmp.right;
        }
    }

}
