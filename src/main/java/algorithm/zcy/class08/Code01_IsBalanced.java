package main.java.algorithm.zcy.class08;

/**
 * 给定一个二叉树头节点 判断是否是平衡二叉树
 * 平衡二叉树：两边高度差不超过1
 * 递归套路：
 * 需要的信息：是否平衡；高度
 *
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code01_IsBalanced {
    public static class Info {
        boolean isBalance;
        int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 给定一个树的根节点
     * 返回是否平衡
     */
    public static boolean isBalance(Node root) {
        return getInfo(root).isBalance;
    }


    /**
     * 给定一个树的根节点
     * 返回平衡信息
     */
    public static Info getInfo(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info info = getInfo(node.left);
        Info info1 = getInfo(node.right);
        int height = Math.max(info.height, info1.height) + 1;
        boolean isBalance = info.isBalance && info1.isBalance && (Math.abs(info.height - info1.height) < 2);
        return new Info(isBalance, height);
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
    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalance(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
