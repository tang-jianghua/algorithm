package main.java.algorithm.zcy.class03;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * @auth tangjianghua
 * @date 2020/7/18
 */
public class Code03_PartitionAndQuickSort {

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
   /*    int[] arr1 = AlgorithmUtil.generatorRandomArr(maxSize, maxValue);
        AlgorithmUtil.printArr(arr1);
       int partition = partition(arr1, 0, arr1.length - 1);
        AlgorithmUtil.printArr(arr1);
        System.out.println(partition);

          int[] arr1 = new int[]{1,2,5,6,2,3,7,10,5,5,5,68,9,3,-1,5};
        int[] ints = netherlandsFlag(arr1, 0, arr1.length - 1);
        AlgorithmUtil.printArr(arr1);
        AlgorithmUtil.printArr(ints);*/


        int testTime = 500000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgorithmUtil.generatorRandomArr(maxSize, maxValue);
            int[] arr2 = AlgorithmUtil.copyArr(arr1);
            //System.out.println(AlgorithmUtil.isEqual(arr1, arr2));
            //quickSort1(arr1);
            //quickSort2(arr1);
            quickSort3(arr1);
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

    /**
     * partition
     * 将数组arr中左右小于等于arr[r]的数放到arr[r]的左边
     * T(N)=O(N)
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition(int arr[], int l, int r) {

        if (l == r) {
            return l;
        }

        int leftIndex = l - 1;
        int index = l;
        while (index <= r) {
            if (arr[index] <= arr[r]) {
                AlgorithmUtil.swapArr(arr, ++leftIndex, index);
            }
            index++;
            //AlgorithmUtil.printArr(arr);
        }
        return leftIndex;

    }


    /**
     * 将小于等于arr[r]的放到左边，大于arr[r]的放到右边
     * // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
     * //  <arr[R]  ==arr[R]  > arr[R]
     *     * T(N)=O(N)
     * @param arr
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        int i = arr[r];

        int leftIndex = l - 1;
        int rightIndex = r + 1;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] < i) {
                AlgorithmUtil.swapArr(arr, ++leftIndex, index++);
            } else if (arr[index] == i) {
                index++;
            } else {
                AlgorithmUtil.swapArr(arr, index, --rightIndex);
            }
            //AlgorithmUtil.printArr(arr);
        }
        return new int[]{++leftIndex, --rightIndex};
    }


    /**
     * 将小于等于arr[r]的放到左边，大于arr[r]的放到右边
     * // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
     * //  <arr[R]  ==arr[R]  > arr[R]
     *     * T(N)=O(N)
     * @param arr
     * @return
     */
    public static int[] netherlandsFlagWithNum(int[] arr, int l, int r,int i) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        int leftIndex = l - 1;
        int rightIndex = r + 1;
        int index = l;
        while (index < rightIndex) {
            if (arr[index] < i) {
                AlgorithmUtil.swapArr(arr, ++leftIndex, index++);
            } else if (arr[index] == i) {
                index++;
            } else {
                AlgorithmUtil.swapArr(arr, index, --rightIndex);
            }
            //AlgorithmUtil.printArr(arr);
        }
        return new int[]{++leftIndex, --rightIndex};
    }


    /**
     * 利用partition进行归并快排
     * T(N)=O(N^2)
     *
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int arr[], int l, int r) {
        if (l >= r) {
            return;
        }
        int partition = partition(arr, l, r);
        process1(arr, l, partition - 1);
        process1(arr, partition + 1, r);
    }

    /**
     * 利用netherlandsFlag进行归并快排
     *      * T(N)=O(N^2)
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int arr[], int l, int r) {
        if (l >= r) {
            return;
        }
        int[] ints = netherlandsFlag(arr, l, r);
        process1(arr, l, ints[0] - 1);
        process1(arr, ints[1] + 1, r);
    }


    /**
     * 利用netherlandsFlag进行归并快排
     * T(N)=O(N*logN)
     *
     * @param arr
     */
    public static void quickSort3(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }
    public static void process3(int arr[], int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l + (int)(Math.random() * (r - l + 1));
        int[] ints = netherlandsFlagWithNum(arr, l,r,arr[i]);
        process3(arr, l, ints[0] - 1);
        process3(arr, ints[1] + 1, r);
    }
}
