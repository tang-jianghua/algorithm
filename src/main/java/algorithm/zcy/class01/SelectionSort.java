package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * @author tangjianghua
 * date 2020/6/11
 * time 19:08
 */
public class SelectionSort {

    public static void main(String[] args) {
    }

    /**
     * 选择排序 从小到大
     *  T(n)=2n+1+2(n-1)+1+...+2(n-(n-1))+1=2n^2+n-
     * @param arr
     */
    public static void selectionSort(int[] arr){
        if(arr==null || arr.length<=2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                minIndex=arr[i]>arr[j]?j:i;
            }
            AlgorithmUtil.swapArr(arr,i,minIndex);
        }
    }
}
