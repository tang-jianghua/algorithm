package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * 认识二分法
 * 2) 在一个有序数组中，找>=某个数最左侧的位置(下角标)
 *
 * @author tangjianghua
 * date 2020/6/12
 * time 11:57
 */
public class Code005_BSNearLeft {

    public static void main(String[] args) {
        int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        int i = (int) ((10 + 1) * Math.random()) - (int) ((10 + 1) * Math.random());
        Code001_SelectionSort.selectionSort(ints);
        System.out.println("num:" + i);
        AlgorithmUtil.printArr(ints);
        int i1 = nearLeftIndex(ints, i);
        System.out.println(i1);

    }

    public static int nearLeftIndex(int[] arr, int num) {
        //非递归算法
        int l = 0;
        int r = arr.length - 1;
        int index =-1;
        int mid;
        while ( l<=r) {
            //二分法查找
            mid = l + ((r - l) >> 1);
            if (arr[mid] >= num) {
                index=mid;
                r = mid - 1;
            }  else {
                l = mid + 1;
            }
        }
        return index;

    }

}
