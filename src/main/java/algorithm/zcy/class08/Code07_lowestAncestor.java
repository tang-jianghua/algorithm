package main.java.algorithm.zcy.class08;

import java.util.*;

/**
 * 寻找一棵二叉树上的两个节点的最小公共父节点
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code07_lowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 方法1 把所有的节点o1的父级映射关系存放到hash表里，从o2开始往上找是否存在
     *
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public static Node lowestAncestor1(Node head,Node o1,Node o2){
        if(o1==null||o2==null) return null;
        if(o1==o2){
            return o1;
        }
        Map<Node,Node> map = new HashMap();
        map.put(head,head);
        fillParentMap(map,head);

        Set<Node> nodeSet=new HashSet<>();
        nodeSet.add(head);
        Node node=o1;
        while (node!=head){
            nodeSet.add(node);
            node= map.get(node);
        }

        node=o2;
        while (!nodeSet.contains(node)){
            node=map.get(node);
        }
        return node;
    }

    public static void fillParentMap(Map<Node,Node> map,Node node){
        if(node.left!=null){
            map.put(node.left,node);
            fillParentMap(map,node.left);
        }
        if(node.right!=null){
            map.put(node.right,node);
            fillParentMap(map,node.right);
        }
    }


    /**
     * 方法2 递归套路
     * 以X节点为头节点
     * 可能性：
     * --两个节点都不在X的左右子树上
     * --一个在一个不在
     * --两个都在
     *   --两个都在左树
     *   --两个都在右树
     *   --一个在左一个在右
     *   --两边都不在=X
     * 返回信息：
     *   是否有O1
     *   是否有O2
     *   最小公共父节点
     *
     *
     * @param x
     * @param o1
     * @param o2
     * @return
     */
    public static Info process(Node x,Node o1,Node o2){
        if(x==null){
            return new Info(null,false,false);
        }
        Info leftInfo = process(x.left, o1, o2);
        Info rightInfo = process(x.right, o1, o2);
        boolean findo1=x==o1||leftInfo.findO1||rightInfo.findO1;
        boolean findo2=x==o2||leftInfo.findO2||rightInfo.findO2;
        Node ans=null;
        if(findo1&&findo2){
            if(leftInfo.findO1&&leftInfo.findO2){
                ans=leftInfo.ans;
            }
            if(rightInfo.findO1&&rightInfo.findO2){
                ans=rightInfo.ans;
            }
            ans=x;
        }
        return new Info(ans,findo1,findo2);

    }
    public static Node lowestAncestor2(Node head,Node o1,Node o2){

        if(head==null||o1==null||o2==null) return null;

        return process(head,o1,o2).ans;
    }


    // 任何子树，
    public static class Info {
        public Node ans;
        public boolean findO1;
        public boolean findO2;

        public Info(Node a, boolean f1, boolean f2) {
            ans = a;
            findO1 = f1;
            findO2 = f2;
        }
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
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
