package main.java.algorithm.zcy.class05;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 桶排序
 *
 * @auth tangjianghua
 * @date 2020/7/22
 */
public class Code03_CountSort {

    /**
     * 做一个包含所有数的桶数组，数本身作为桶数组的索引，通过数组的索引排序
     * 缺点：由于数组索引从0开始，所以只能拍自然数
     *
     * @param arr
     */
    public static void countSort(int arr[]) {

        if (arr.length < 2) {
            return;
        }

        //找出最大数
        int max = Arrays.stream(arr).max().orElse(0);
     /*   int max=arr[0];
        for (int i = 1; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }*/


        //做一个包含所有元素的桶数组
        int[] ints = new int[max + 1];

        //把原始数组遍历添加到桶数组中，相同元素++
        Arrays.stream(arr).forEach(value ->
                ints[value]++
        );
        //AlgorithmUtil.printArr(ints);

        //把桶数组遍历拿出所有的元素，完成排序
        for (int i = 0, j = 0; i < ints.length; i++) {
            //如果桶元素>0，放进原数组
            while (ints[i]-- > 0) {
                arr[j++] = i;
            }
        }
    }


    public static void main(String[] args) {
         /*  int[] arr = AlgorithmUtil.generatorRandomArr(10,0, 20);
        AlgorithmUtil.printArr(arr);
     countSort(arr);
        AlgorithmUtil.printArr(arr);*/


        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        int minValue = 0;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgorithmUtil.generatorRandomArr(maxSize, minValue, maxValue);
            int[] arr2 = AlgorithmUtil.copyArr(arr1);
            //System.out.println(AlgorithmUtil.isEqual(arr1, arr2));
            countSort(arr1);
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
