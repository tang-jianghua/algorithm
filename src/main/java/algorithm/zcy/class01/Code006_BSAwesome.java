package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * 认识二分法
 * 4) 一个数组中存在这样的数，它比相邻的数都小，那么称他为局部最小值，查找一个局部最小值的下角标
 *
 * @author tangjianghua
 * date 2020/6/12
 * time 11:57
 */
public class Code006_BSAwesome {

    public static void main(String[] args) {
        int[] ints = AlgorithmUtil.generatorRandomArr(20, 100);
        AlgorithmUtil.printArr(ints);
        int i1 = awesome(ints);
        System.out.println(i1);

    }

    public static int awesome(int[] arr) {
        if(arr.length==1 || arr[0]<=arr[1]){
            return arr[0];
        }
        if(arr[arr.length-1]<=arr[arr.length-2]){
            return arr[arr.length-1];
        }
        int l = 1;
        int r = arr.length - 2;
        int mid;
        while ( l<=r) {
            //二分法查找
            mid = l + ((r - l) >> 1);
            if (arr[mid] > arr[mid-1]) {
                r=mid-1;
            }else if(arr[mid] > arr[mid+1]) {
                l=mid+1;
            }else{
                return  arr[mid];
            }
        }
        return arr[1];

    }

}
