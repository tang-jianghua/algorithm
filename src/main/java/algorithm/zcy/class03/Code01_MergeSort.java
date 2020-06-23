package main.java.algorithm.zcy.class03;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author tangjianghua
 * date 2020/6/23
 * time 19:27
 */
public class Code01_MergeSort {


    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        final int[] help = new int[r - l + 1];
        int index1 = l, index2 = m + 1;
        int i = 0;
        while (index1 <= m && index2 <= r) {
            help[i++] = arr[index1] <= arr[index2] ? arr[index1++] : arr[index2++];
        }
        while (index1 <= m) {
            help[i++] = arr[index1++];
        }
        while (index2 <= r) {
            help[i++] = arr[index2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l++] = help[j];
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgorithmUtil.generatorRandomArr(maxSize, maxValue);
            int[] arr2 = AlgorithmUtil.copyArr(arr1);
            System.out.println(AlgorithmUtil.isEqual(arr1, arr2));
            mergeSort(arr1);
            Arrays.sort(arr2);
            if (!AlgorithmUtil.isEqual(arr1, arr2)) {
                succeed = false;
                AlgorithmUtil.printArr(arr1);
                AlgorithmUtil.printArr(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }

}
