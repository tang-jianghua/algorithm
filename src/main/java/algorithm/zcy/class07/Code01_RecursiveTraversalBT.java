package main.java.algorithm.zcy.class07;

/**
 * 认识二叉树的前中后序,递归序
 * 前序-头左右
 * 中序-左头右
 * 后序-左右头
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code01_RecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void print(Node head){
        if(head==null)return;

        //前序
        //System.out.println(head.value);
        print(head.left);
        //中序
        //System.out.println(head.value);
        print(head.right);
        //后序
        //System.out.println(head.value);

    }

    public static void prePrint(Node head){
        if(head==null)return;

        //前序
        System.out.println(head.value);
        prePrint(head.left);
        //中序
        //System.out.println(head.value);
        prePrint(head.right);
        //后序
        //System.out.println(head.value);

    }

    public static void inPrint(Node head){
        if(head==null)return;

        //前序
        //System.out.println(head.value);
        inPrint(head.left);
        //中序
        System.out.println(head.value);
        inPrint(head.right);
        //后序
        //System.out.println(head.value);

    }

    public static void postPrint(Node head){
        if(head==null)return;

        //前序
        //System.out.println(head.value);
        postPrint(head.left);
        //中序
        //System.out.println(head.value);
        postPrint(head.right);
        //后序
        System.out.println(head.value);

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
        postPrint(head);
        System.out.println("========");

    }
}
