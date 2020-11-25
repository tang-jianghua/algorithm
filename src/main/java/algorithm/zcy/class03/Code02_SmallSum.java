package main.java.algorithm.zcy.class03;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 求所有数的小和的和
 *
 * @author tangjianghua
 * date 2020/6/23
 * time 20:12
 */
public class Code02_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                +
                process(arr, mid + 1, r)
                +
                merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        final int[] help = new int[r - l + 1];
        int index1 = l,
                index2 = m + 1,
                i = 0,
                res = 0;
        while (index1 <= m && index2 <= r) {
            //求和
            res += arr[index1] < arr[index2] ? (r - index2 + 1) * arr[index1] : 0;
            help[i++] = arr[index1] < arr[index2] ? arr[index1++] : arr[index2++];
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
        return res;
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
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                AlgorithmUtil.printArr(arr1);
                AlgorithmUtil.printArr(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }
}
