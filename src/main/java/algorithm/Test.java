package main.java.algorithm;

import main.java.algorithm.util.AlgorithmUtil;
import main.java.algorithm.zcy.class01.Code003_InsertionSort;
import main.java.algorithm.zcy.class01.Code004_BSExist;

import java.util.Arrays;

/**
 * @author tangjianghua
 * date 2020/6/12
 * time 10:45
 */
public class Test {


    public static void main(String[] args) {
        exist();

    }

    public static void sort() {
        int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        AlgorithmUtil.printArr(ints);
        int[] ints1 = AlgorithmUtil.copyArr(ints);
//        BubbleSort.bubbleSort(ints);
        Code003_InsertionSort.insertionSort(ints);
        Arrays.sort(ints1);
        System.out.println(AlgorithmUtil.isEqual(ints, ints1));
        System.out.println(1 ^ 1);
    }

    public static void exist() {
        int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        int i = (int) ((10 + 1) * Math.random()) - (int) ((10 + 1) * Math.random());
        System.out.println("num:"+i);
        AlgorithmUtil.printArr(ints);
        boolean exist = Code004_BSExist.exist(ints, ints[0] );
        System.out.println(exist);
    }
}

