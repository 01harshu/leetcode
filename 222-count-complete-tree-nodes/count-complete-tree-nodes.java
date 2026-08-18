/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        TreeNode left = root, right = root;
        int l = 0, r = 0;

        while(left != null){
            l++;
            left = left.left;
        }

        while(right != null){
            r++;
            right = right.right;
        }

        if(l == r){
            return (1 << l) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
        
    }
}