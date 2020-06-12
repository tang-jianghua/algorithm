package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 冒泡排序
 * 从最后一个元素开始，每次排序这个元素之前的数组，不断缩小这个元素范围。
 * 特点：每次排序都会把当前范围最大的元素冒到当前范围的最顶端，不断往前缩小这个范围
 * @author tangjianghua
 * date 2020/6/12
 * time 10:26
 */
public class Code002_BubbleSort {



    /**
     * T(N)=O(n^2)
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if(arr==null || arr.length==1){
            return;
        }
        //从n-1开始逐渐缩小排序范围
        for (int i=arr.length-1;i>0;i--){
            //对选择的范围进行排序
            for (int j=0;j<i;j++){
                //从0开始比较连续的两个数，不断地将较大的数往后排
                //看加比+交换 T(n)=(2+3)(n+n-1+...+1)=5(n+(n^2)/2-n/2)=5n^2+5n/2
                if(arr[j]>arr[j+1]){
                    AlgorithmUtil.swapArr(arr,j,j+1);
                }
            }
            System.out.println("debug:"+Arrays.toString(arr));
        }
    }
}
