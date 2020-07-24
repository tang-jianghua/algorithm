package main.java.algorithm.zcy.class07;

import java.util.Stack;

/**
 * 非递归法遍历二叉树的前中后序
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code02_UnRecursiveTraversalBT {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void prePrint(Node head) {
        if (head == null) return;
        //用栈来模拟递归
        Stack<Node> nodes = new Stack<>();
        Node node = head;
        nodes.push(node);
        //前序头左右 所以要先打印头，然后再把右节点压入栈中，在压入左节点
        while (!nodes.isEmpty()) {
            Node node1 = nodes.pop();
            System.out.println(node1.value);
            if (node1.right != null) {
                nodes.push(node1.right);
            }
            if (node1.left != null) {
                nodes.push(node1.left);
            }
        }
    }

    public static void inPrint(Node head) {
        if (head == null) return;
        //用栈来模拟递归,中序前面的都不能打印，要不断的往栈里压，知道没有子节点可压了再弹出打印
        Stack<Node> nodes = new Stack<>();
        while (!nodes.isEmpty() || head != null) {
            if (head != null) {
                //先把所有左节点压入
                nodes.push(head);
                head = head.left;
            } else {
                //没有左节点可压的时候，弹出先打印
                Node pop = nodes.pop();
                System.out.println(pop.value);
                //如果有右节点，从右节点开始重复压入
                head = pop.right;
            }
        }
    }

    /**
     * 后序遍历
     * 采用两个栈，将要打印的节点按照后序倒着压入
     * 后序：左右头   压入的顺序：头右左。先进后出
     *
     * @param head
     */
    public static void postPrint1(Node head) {
        if (head == null) return;
        //构建两个栈
        Stack<Node> help = new Stack<>();
        Stack<Node> nodes = new Stack<>();
        help.push(head);
        while (!help.isEmpty()){
            Node pop = help.pop();
            nodes.push(pop);
            if(pop.left!=null){
                help.push(pop.left);
            }
            if(pop.right!=null){
                help.push(pop.right);
            }
        }
        while (!nodes.isEmpty()){
            System.out.println(nodes.pop().value);
        }

    }
    /**
     * 先构建一个栈，从头结点开始压入左节点
     * 当左节点没有子节点时，不再压入
     * 开始弹出，获取队列的头节点，
     * --如果上次打印的节点是该节点的左节点，那么查看该节点有没有右节点
     *   如果存在右节点，将右节点压入；如果不存在右节点则弹出该节点。
     * --如果上次打印的节点是该节点的右节点，则弹出该节点并打印
     * 构建一个指针指向上次弹出的节点
     *
     * @param head
     */
    public static void postPrint2(Node head) {
        if (head == null) return;
        //构建一个栈
        Stack<Node> nodes = new Stack<>();
        nodes.push(head);
        //上次打印的节点
        Node last=null;
        Node peek;
        while (!nodes.isEmpty()) {
            peek = nodes.peek();
            if(peek.left!=null&& last!=peek.left && last!=peek.right){
                nodes.push(peek.left);
            }else if(peek.right!=null&& last==peek.left){
                nodes.push(peek.right);
            }else{
                last = nodes.pop();
                System.out.println(last.value);
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

        prePrint(head);
        System.out.println("========");
        inPrint(head);
        System.out.println("========");
        postPrint1(head);
        System.out.println("========");
        postPrint2(head);
        System.out.println("========");
    }
}
