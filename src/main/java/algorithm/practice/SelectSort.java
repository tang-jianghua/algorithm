package main.java.algorithm.practice;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * @auth tangjianghua
 * @date 2020/8/28
 */
public class SelectSort {

    /**
     * 每次选最小的放到前面
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }
            if (index != i) {
                swap(arr, index, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        System.out.println(Arrays.toString(ints));
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
