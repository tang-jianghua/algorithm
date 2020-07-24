package main.java.algorithm.zcy.class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化与反序列化二叉树
 * <p>
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 以下代码全部实现了。
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 * __2
 * /
 * 1
 * 和
 * 1__
 * \
 * 2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code04_SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 层级序列化
     * 给定一个树根节点
     * 返回一个节点值队列
     */
    public static Queue<Integer> levelSerial(Node head) {
        Queue<Integer> queue = new LinkedList<>();
        level(head, queue);
        return queue;
    }


    public static void level(Node head, Queue<Integer> queue) {
        Queue<Node> help = new LinkedList<>();
        help.offer(head);
        while (!help.isEmpty()) {
            Node poll = help.poll();
            if (poll == null) {
                queue.add(null);
                continue;
            }
            queue.add(poll.value);
            help.offer(poll.left);
            help.offer(poll.right);
        }
    }

    /**
     * 反序列化模拟序列化动作
     * @param queue
     * @return
     */
    public static Node levelDeserial(Queue<Integer> queue) {

        Integer poll1 = queue.poll();
        if(poll1==null) return null;
        Queue<Node> help = new LinkedList<>();
        Node node = new Node(poll1);
        help.offer(node);
        while (!help.isEmpty()){
            Node poll2 = help.poll();
            poll2.left=generateNode(queue.poll());
            poll2.right=generateNode(queue.poll());
            if(poll2.left!=null){
                help.offer(poll2.left);
            }
            if(poll2.right!=null){
                help.offer(poll2.right);
            }
        }
        return node;

    }

    public static Node generateNode(Integer val) {
        if (val == null) {
            return null;
        }
        return new Node(val);
    }

    /**
     * 前序序列化
     * 给定一个树根节点
     * 返回一个节点值队列
     */
    public static Queue<Integer> preSerial(Node head) {
        Queue<Integer> queue = new LinkedList<>();
        pre(head, queue);
        return queue;

    }

    public static void pre(Node head, Queue<Integer> queue) {

        if (head == null) {
            queue.offer(null);
            return;
        }

        queue.offer(head.value);
        pre(head.left, queue);
        pre(head.right, queue);
    }

    /**
     * 前序反序列化
     *
     * @return
     */
    public static Node preDeserialize(Queue<Integer> queue) {
        Integer poll1 = queue.poll();
        if (poll1 == null) {
            return null;
        }
        Node head = new Node(poll1);
        head.left = preDeserialize(queue);
        head.right = preDeserialize(queue);
        return head;
    }

    /**
     * 后序序列化
     * 给定一个树根节点
     * 返回一个节点值队列
     */
    public static Queue<Integer> posSerial(Node head) {
        Queue<Integer> queue = new LinkedList<>();
        post(head, queue);
        return queue;

    }

    public static void post(Node head, Queue<Integer> queue) {

        if (head == null) {
            queue.offer(null);
            return;
        }

        post(head.left, queue);
        post(head.right, queue);
        queue.offer(head.value);
    }

    /**
     * 后序反序列化
     * 后序是左右头，无法从头开始指定子节点，可以把队列先压入栈中，然后从head反向序列化
     *
     * @return
     */
    public static Node postDeserialize(Queue<Integer> queue) {
        Stack<Node> nodes = new Stack<>();
        while (!queue.isEmpty()) {
            Integer poll1 = queue.poll();
            if (poll1 == null) {
                nodes.push(null);
            } else {
                nodes.push(new Node(poll1));
            }
        }
        return postDeserialize(nodes);

    }

    public static Node postDeserialize(Stack<Node> stack) {
        Node pop = stack.pop();
        if (pop == null) return null;
        pop.right = postDeserialize(stack);
        pop.left = postDeserialize(stack);
        return pop;
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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<Integer> pre = preSerial(head);
            Queue<Integer> pos = posSerial(head);
            Queue<Integer> level = levelSerial(head);
            Node preBuild = preDeserialize(pre);
            Node posBuild = postDeserialize(pos);
            Node levelBuild = levelDeserial(level);
            if (!isSameValueStructure(preBuild, posBuild)
                || !isSameValueStructure(posBuild, levelBuild)
            ) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
