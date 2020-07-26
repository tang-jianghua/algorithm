package main.java.algorithm.zcy.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 二叉树的最大宽度
 * 利用层级遍历，记录每层的宽度
 *
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code06_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if(head==null){
            return 1;
        }
        //先准备一个队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        //记录层级
        int curLevel = 1;
        //记录宽度
        int curWidth = 0;
        int maxWidth = 0;
        //记录节点所属层级
        Map<Node, Integer> nodeLevelMap = new HashMap();
        nodeLevelMap.put(head, 1);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            Integer pollLevel = nodeLevelMap.get(poll);
            if (poll.left != null) {
                queue.add(poll.left);
                nodeLevelMap.put(poll.left, pollLevel + 1);
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nodeLevelMap.put(poll.right, pollLevel + 1);
            }
            if (curLevel == pollLevel) {
                curWidth++;
            } else {
                maxWidth = Math.max(maxWidth, curWidth);
                //此时是下一层的第一个节点
                curWidth = 1;
                curLevel++;
            }
        }
        return Math.max(maxWidth, curWidth);
    }

    public static int maxWidthNoMap(Node head) {
        if(head==null){
            return 1;
        }
        //先准备一个队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        //记录
        Node rightest = head;
        Node nextRightest = null;
        //记录宽度
        int curWidth = 0;
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
                nextRightest = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextRightest = poll.right;
            }
            curWidth++;

            if (poll == rightest) {
                maxWidth = Math.max(maxWidth, curWidth);
                curWidth = 0;
                rightest = nextRightest;
            }
        }
        return Math.max(maxWidth, curWidth);
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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

}
