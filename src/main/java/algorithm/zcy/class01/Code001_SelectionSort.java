package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 选择排序
 * 特点：每次都会把当前范围里的最小的元素选择出来放到范围前一个位置，不断往后缩小这个范围。
 * @author tangjianghua
 * date 2020/6/11
 * time 19:08
 */
public class Code001_SelectionSort {

    public static void main(String[] args) {
        int[] ints = AlgorithmUtil.generatorRandomArr(100, 100);
        AlgorithmUtil.printArr(ints);
        int[] ints1 = AlgorithmUtil.copyArr(ints);
        selectionSort(ints);
        Arrays.sort(ints1);
        System.out.println(AlgorithmUtil.isEqual(ints,ints1));
    }

    /**
     * 选择排序 从小到大
     *  T(n)=O(n^2)
     * @param arr
     */
    public static void selectionSort(int[] arr){
        if(arr==null || arr.length<=1){
            return;
        }
        //从0~n-1进行遍历
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            //每次选择i~n中最小的
            for (int j = i+1; j < arr.length; j++) {
                //看+比 T1(n)=2(n+n-1+n-2+....+1)=2n^2-n/2+n=2n^2-n/2=O(n^2)
                minIndex=arr[minIndex]>arr[j]?j:minIndex;
            }
            //交换 最大3次^  T2(n)=3n
            AlgorithmUtil.swapArr(arr,i,minIndex);
            //T(n)= T1(n)+ T2(n)=2n^2+5n/2=O(n^2)
            System.out.println("debug:"+Arrays.toString(arr));
        }
    }
}
