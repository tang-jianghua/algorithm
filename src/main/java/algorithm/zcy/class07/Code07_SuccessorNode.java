package main.java.algorithm.zcy.class07;

/**
 * 后继节点 （中序节点）
 * 获取下一个后继节点，即获取下一个中序节点。与后继节点对应的叫前驱节点
 * 中序：左头右
 * 给定一个节点x：
 * -- 如果x存在右节点，按照中序遍历的规则，后继节点一定是x的右子树的最左节点。
 * -- 如果x不存在右节点，那么说明x处在一个已经遍历完的子树的最后节点，后继节点分两种情况
 * -- 如果这棵子树的头节点处在左节点位置，那么这棵子树的头节点就是后继节点
 * -- 如果这颗子树的头节点处在右节点位置，那么不断往上扩大这棵子树，直到子树处在左节点为止，
 * 或者直到扩大整棵树，说明x没有后继节点
 *
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code07_SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }


    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return getMostLeft(node.right);
        } else {
            return getRightParent(node);
        }

    }

    private static Node getMostLeft(Node right) {
        if (right.left == null) {
            return right;
        } else {
            return getMostLeft(right.left);
        }
    }

    private static Node getRightParent(Node node) {
        if (node.parent == null) {
            return null;
        }
        if (node.parent.left == node) {
            return node.parent;
        } else {
            return getRightParent(node.parent);
        }
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
