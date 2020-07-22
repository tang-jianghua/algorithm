package main.java.algorithm.util;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author tangjianghua
 * date 2020/6/11
 * time 19:16
 */
public class AlgorithmUtil {

    private static Logger logger = Logger.getLogger(AlgorithmUtil.class.getName());

    /**
     * 交换数组中n和m的值
     *
     * @param arr
     * @param n
     * @param m
     */
    public static void swapArr(int[] arr, int n, int m) {
        if (n < 0 || n >= arr.length || m < 0 || m >= arr.length) {
//            logger.warning("超出范围");
            return;
        }
        if (n == m) {
//            logger.warning("无需交换");
            return;
        }
        arr[n] = arr[n] ^ arr[m];
        arr[m] = arr[n] ^ arr[m];
        arr[n] = arr[n] ^ arr[m];
    }

    /**
     * 对数器
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generatorRandomArr(int maxSize, int maxValue) {
        //Math.random() [0,1)
        //(n+1)*Math.random() [0,n+1)
        //int((n+1)*Math.random()) [0,n]
        //int((n+1)*Math.random())-int((n+1)*Math.random()) [0,n]-[0,n]=[-n,n]
        int size = (int) ((maxSize + 1) * Math.random());
        if (maxSize <= 2 || size <=1) {
            size = 2;
        }
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = ((int) ((maxValue + 1) * Math.random())-(int) ((maxValue + 1) * Math.random()));
        }
        return ints;
    }
    /**
     * 对数器
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generatorRandomArr(int maxSize,int minValue, int maxValue) {
        //Math.random() [0,1)
        //(n+1)*Math.random() [0,n+1)
        //int((n+1)*Math.random()) [0,n]
        //int((n+1)*Math.random())-int((n+1)*Math.random()) [0,n]-[0,n]=[-n,n]
        int size = (int) ((maxSize + 1) * Math.random());
        if (maxSize <= 2 || size <=1) {
            size = 2;
        }
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) ((maxValue + 1-minValue) * Math.random())+minValue;
        }
        return ints;
    }

    public static int[] copyArr(int[] arr){
        if(arr==null){
            return null;
        }
        int[] ints = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ints[i]=arr[i];
        }
        return ints;
    }

    /**
     * 打印数组
     * @param arr
     */
    public static void printArr(int[] arr){
        if(arr==null){
            return;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 比较两个数组是否相同
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null) || arr1.length != arr2.length) {
            return false;
        }
        if ((arr1 == null && arr2 == null) || arr1 == arr2) {
            return true;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


}
