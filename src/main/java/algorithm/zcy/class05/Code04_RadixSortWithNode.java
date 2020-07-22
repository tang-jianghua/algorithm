package main.java.algorithm.zcy.class05;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 基数排序
 * 将每个元素从个位开始比较。
 *
 * @auth tangjianghua
 * @date 2020/7/22
 */
public class Code04_RadixSortWithNode {

    /**
     * 基于十进制的基数排序
     */
    public static void radixSort(int[] arr) {

        //找出最大数的位数
        int max = Arrays.stream(arr).max().getAsInt();
        int bit = 1;
        while ((max=max / 10) >= 1) {
            bit++;
        }

        //创建一个[0,9]的链表数组
        Node[] nodes = new Node[10];

        //从个位开始，将每个元素放到个位对应的链表中，放完以后遍历链表数组到原数组中，然后再从十位开始对比，同理向上，最终将原数组复写为有序数组
        for (int j = 1; j <= bit; j++) {
            //遍历j位
            for (int i = 0; i < arr.length; i++) {
                int digit = getDigit(arr[i], j);
                Node root = nodes[digit];
                if(root==null){
                    nodes[digit]=new Node(arr[i]);
                }else{
                    while (root.next!=null){
                        root=root.next;
                    }
                    root.next=new Node(arr[i]);
                }
            }
            //装完桶以后拿出来
            for (int i = 0,h=0; i < nodes.length; i++) {
                if(nodes[i]!=null){
                    Node c=nodes[i];
                    do{
                        arr[h++]=c.value;
                    }while ((c=c.next)!=null);
                    nodes[i]=null;
                }
            }
        }
    }

    static class Node {

        private int value;

        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 获取a的d位上的数
     * d--1 个位
     * --2 十位
     * --3 百位
     * ...
     *
     * @param a
     * @param d
     * @return
     */
    public static int getDigit(int a, int d) {
        return (a / ((int) Math.pow(10, d - 1))) % 10;
    }


    public static void main(String[] args) {

        int testTime = 500000;
        int maxSize = 3;
        int maxValue = 1000;
        int minValue = 0;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgorithmUtil.generatorRandomArr(maxSize, minValue, maxValue);
            int[] arr2 = AlgorithmUtil.copyArr(arr1);
            //System.out.println(AlgorithmUtil.isEqual(arr1, arr2));
            radixSort(arr1);
            Arrays.sort(arr2);
            if (!AlgorithmUtil.isEqual(arr1, arr2)) {
                succeed = false;
                //AlgorithmUtil.printArr(arr1);
                //AlgorithmUtil.printArr(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
