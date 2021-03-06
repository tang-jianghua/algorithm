package main.java.algorithm.leetcode.algorithm.binarytree.simple;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @auth tangjianghua
 * @date 2020/7/28
 */
public class Q0104_BinaryTreeDepth {


    /**
     * 递归套路
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {

        return process(root).depth;
    }

    public static Info process(TreeNode node) {
        //base case
        if (node == null) {
            return new Info(0);
        }

        return new Info(1 + Math.max(process(node.left).depth, process(node.right).depth));
    }


    public static class Info {
        int depth;

        public Info(int depth) {
            this.depth = depth;
        }
    }


    //Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
