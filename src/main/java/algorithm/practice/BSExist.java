package main.java.algorithm.practice;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * @auth tangjianghua
 * @date 2020/8/28
 */
public class BSExist {


    /**
     * 每次选最最大放到最顶端
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {

            int index = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[index] < arr[j]) {
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

    public static int nearLeft(int[] arr, int i) {
        if (arr == null || arr.length == 0) return -1;
        return process(arr, i, 0, arr.length - 1);
    }

    public static int process(int[] arr, int i, int left, int right) {
        //先写base case
        if (left > right || left > arr.length - 1 || right < 0) return -1;
        if (left == right) {
            return arr[left] == i ? left : -1;
        }
        int mid = left + ((right - left) >>> 1);
        if (arr[mid] >= i) {
            return process(arr, i, left, mid);
        } else if (arr[mid] < i) {
            return process(arr, i, mid + 1, right);
        }
    }

    public static void main(String[] args) {
        int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        sort(ints);
        System.out.println(Arrays.toString(ints));
        int exit = nearLeft(ints, 52);
        System.out.println(exit);
    }
}
