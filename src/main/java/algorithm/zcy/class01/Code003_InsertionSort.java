package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * T(n)=O(n^2)
 * 插入排序
 * 从第二个元素开始，每次排序这个元素之前的数组，不断扩增这个元素范围知道最后一个。
 * 特点：每次排序都会将范围之外的下一个元素插入到当前范围重新排序，不断插入下一个元素
 * @author tangjianghua
 * date 2020/6/12
 * time 10:39
 */
public class Code003_InsertionSort {


    public static void insertionSort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }
        //从1开始到n-1,每次将i前面的位置进行排序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                AlgorithmUtil.swapArr(arr, j, j + 1);
            }
            System.out.println("debug:"+ Arrays.toString(arr));
        }
    }
}
