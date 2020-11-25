package main.java.algorithm.practice;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * 认识二分法
 * 1）在一个有序数组中，找某个数是否存在
 * T(N)=O(logN)
 *
 * @author tangjianghua
 * date 2020/6/12
 * time 11:33
 */
public class BSExist {


    public static boolean bsExist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return exit(arr, 0, arr.length - 1, num);
    }

    public static boolean exit(int[] arr, int l, int r, int num) {
        //base case
        if (l > arr.length - 1 || r < 0 || l > r) {
            return false;
        }
        if (l == r) {
            return arr[l] == num;
        }
        int i = l + ((r - l) >> 1);
        if (arr[i] == num) {
            return true;
        } else if (arr[i] > num) {
            return exit(arr, l, i-1, num);
        } else {
            return exit(arr, i+1, r, num);
        }

    }

    public static void main(String[] args) {
        int[] arr = AlgorithmUtil.generatorRandomArr(10, 100);
        BubbleSort.sort(arr);
        AlgorithmUtil.printArr(arr);
        boolean b = bsExist(arr, arr[0]);
        System.out.println(b);
    }
}
