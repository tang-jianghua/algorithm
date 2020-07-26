package main.java.algorithm.zcy.class08;

import java.util.LinkedList;

/**
 * 是否是完全二叉树
 * 可能性：
 * -- 整棵树是满二叉树（左右子树都是满二叉树且高度相同）
 * -- 左右子树都是完全二叉树
 *   --高度一致，左树是满二叉树，右树是完全二叉树
 *   --高度不一致
 *     --左树是完全二叉树，右树是满二叉树，左树比右树高度高1
 *     --左右子树都是满二叉树，左树高1
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code06_IsCBT {



    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static boolean isCBT(Node node){
        if(node==null) return true;
        return process(node).isCBT;
    }

    private static Info process(Node node) {
        if(node==null){
           return new Info(true,true,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height=Math.max(leftInfo.height,rightInfo.height)+1;
        boolean isFull=leftInfo.isFull&&rightInfo.isFull&&leftInfo.height==rightInfo.height;
        boolean isCBT=false;
        if(isFull){
            isCBT=true;
        }else {
            if(leftInfo.height==rightInfo.height){
                if(leftInfo.isFull&&rightInfo.isCBT){
                    isCBT=true;
                }
            }else if(leftInfo.height==rightInfo.height+1){
                if(leftInfo.isCBT&&rightInfo.isFull){
                    isCBT=true;
                }
            }
        }
        return new Info(isFull,isCBT,height);
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
