package main.java.algorithm.practice;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * 认识二分法
 * 2) 在一个有序数组中，找>=某个数最左侧的位置(下角标)
 *
 * @auth tangjianghua
 * @date 2020/8/28
 */
public class BSNearLeft {


    public static int bsNearLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int mid;
        int index = -1;
        do {
            mid=l+((r-l)>>1);
            if(arr[mid]>=num){
                    index = mid;
                    r=mid-1;
            }else{
                l=mid+1;
            }
        } while (l <= r);

        return index;
    }


    public static void main(String[] args) {
        int[] arr = AlgorithmUtil.generatorRandomArr(10, 100);
        BubbleSort.sort(arr);
        AlgorithmUtil.printArr(arr);
        int i = bsNearLeft(arr, arr[1]);
        System.out.println(i);
    }
}
