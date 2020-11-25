package main.java.algorithm.zcy.class08;

import java.util.ArrayList;

/**
 * 求一棵二叉树中的最大搜索子树的节点数
 * 搜索二叉树：1.没有重复节点  2.左节点<头节点<右节点
 * 递归套路：
 * 1.节点数
 * 2.最大值
 * 3.最小值
 * 4.是不是搜索二叉树
 *
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code04_MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        int maxSubBSTSize;
        int max;
        int min;
        boolean isBST;

        public Info(int maxSubBSTSize, int max, int min, boolean isBST) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public static int maxSubBSTSize(Node node) {

        if (node == null) return 0;
        return process(node).maxSubBSTSize;
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value;
        int min = node.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        int maxSubBSTSize = 0;
        //当前树不是搜索树时
        maxSubBSTSize = Math.max(rightInfo == null ? 0 : rightInfo.maxSubBSTSize, leftInfo == null ? 0 : leftInfo.maxSubBSTSize);

        boolean isBST = false;
        //当前树是搜索树时
        if (
                //左子树是不是搜索树
                (leftInfo == null ? true : leftInfo.isBST)
                        &&
                        //左子树的最大值小于当前值
                        (leftInfo == null ? true : leftInfo.max < node.value)
                        &&
                        //右子树是不是搜索树
                        (rightInfo == null ? true : rightInfo.isBST)
                        &&
                        //右子树的最小值大于当前值
                        (rightInfo == null ? true : rightInfo.min > node.value)

        ) {
            maxSubBSTSize =
                    (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                            +
                            (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                            + 1;
            isBST = true;
        }
        return new Info(maxSubBSTSize, max, min, isBST);
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


    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
