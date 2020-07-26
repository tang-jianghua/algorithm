package main.java.algorithm.zcy.class08;

/**
 * 给定一个二叉树的根节点，判断是不是满二叉树
 * 满二叉树：除最后一层无任何子节点外，每一层上的所有结点都有两个子结点的二叉树。
 * 国内教程定义：一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树。
 * 国外(国际)定义:a binary tree T is full if each node is either a leaf or possesses exactly two childnodes.
 * 大意为：如果一棵二叉树的结点要么是叶子结点，要么它有两个子结点，这样的树就是满二叉树。(一棵满二叉树的每一个结点要么是叶子结点，要么它有两个子结点，但是反过来不成立，因为完全二叉树也满足这个要求，但不是满二叉树)
 *
 *
 * 特点:结点总数是(2^k) -1 。k代表二叉树高度。
 * 递归套路：
 *  节点数
 *  高度
 *
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code02_IsFull {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static class Info {
        public int height;
        public int nodes;

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info process(Node node){
        if(node==null){
            return new Info(0,0);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height=Math.max(leftInfo.height,rightInfo.height)+1;
        int nodes=leftInfo.nodes+rightInfo.nodes+1;
        return new Info(height,nodes);
    }

    public static boolean isFull(Node node){
        if(node==null) return true;
        Info info = process(node);
        return (1<<info.height)-1==info.nodes;
    }
    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
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
            if (isFull1(head) != isFull(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
