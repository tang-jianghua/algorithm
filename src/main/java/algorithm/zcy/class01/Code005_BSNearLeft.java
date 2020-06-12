package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * 认识二分法
 * 2) 在一个有序数组中，找>=某个数最左侧的位置
 * @author tangjianghua
 * date 2020/6/12
 * time 11:57
 */
public class Code005_BSNearLeft {

    public static void main(String[] args) {
        int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        int i = (int) ((10 + 1) * Math.random()) - (int) ((10 + 1) * Math.random());
        System.out.println("num:"+i);
        AlgorithmUtil.printArr(ints);
        int i1 = nearLeftIndex(ints, i);
        System.out.println(i1);

    }

    public static int nearLeftIndex(int[] arr,int num){
        //非递归算法
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (r >= l) {
            //二分法查找
            mid = l + ((r - l) >> 1);
            if (arr[mid] == num) {
                if(mid-1<0){
                    return 0;
                }else if(arr[mid-1]==num){
                    r = mid - 1;
                }else{
                    return mid-1;
                }
            } else if (arr[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return 0;

    }
}
