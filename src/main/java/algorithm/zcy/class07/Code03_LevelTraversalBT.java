package main.java.algorithm.zcy.class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 层级遍历二叉树
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code03_LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 层级遍历 采用队列的形式，不断地把子节点推向队列中
     *
     * @param node
     */
    public static void levelTraversalBT(Node node) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.offer(node);
        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            System.out.println(poll.value);
            if (poll.left != null) {
                nodes.offer(poll.left);
            }
            if (poll.right != null) {
                nodes.offer(poll.right);
            }
        }

    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTraversalBT(head);
        System.out.println("========");
    }


}
