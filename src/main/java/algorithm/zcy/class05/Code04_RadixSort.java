package main.java.algorithm.zcy.class05;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 基数排序
 * 将每个元素从个位开始比较。
 *
 * @auth tangjianghua
 * @date 2020/7/22
 */
public class Code04_RadixSort {

    /**
     * 基于十进制的基数排序
     */
    public static void radixSort(int[] arr, int l, int r, int bits) {
        int radix = 10;

        //创建一个[0,9]的链表数组
        int[] count = new int[radix];
        int[] helper = new int[r - l + 1];


        //从个位开始，将每个元素放到个位对应的链表中，放完以后遍历链表数组到原数组中，然后再从十位开始对比，同理向上，最终将原数组复写为有序数组
        for (int j = 1; j <= bits; j++) {
            //遍历j位 把j位的所有出现的数统计起来
            //犯错-------for (int i = l; i < (r - l + 1); i++) {
            for (int i = l; i <= r; i++) {
                int digit = getDigit(arr[i], j);
                count[digit]++;
            }
            //统计完以后，将所有的数排序.这一步把每个数应该放在helper的第几位确定
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            //由于上一步我们确定了个位数某个数在helper数组上的右边界，所以接下来我们从右往左遍历，放到上一步指定的位置
            for (int i = r; i >= l; i--) {
                int digit = getDigit(arr[i], j);
                helper[--count[digit]] = arr[i];
            }
            //将helper copy到arr
            for (int i = l; i <= r; i++) {
                arr[i] = helper[i - l];
            }
            //清空桶
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
        }
    }


    @SuppressWarnings("获取最大位数要>=1")
    public static void radixSort(int[] arr) {
        //找出最大数的位数
        int max = Arrays.stream(arr).max().orElse(0);
        int bit = 1;
        while ((max = max / 10) >= 1) {
            bit++;
        }
        //System.out.println(bit);
        radixSort(arr, 0, arr.length - 1, bit);
    }

    /**
     * 获取a的d位上的数
     * d--1 个位
     * --2 十位
     * --3 百位
     * ...
     *
     * @param a
     * @param d
     * @return
     */
    public static int getDigit(int a, int d) {
        return (a / ((int) Math.pow(10, d - 1))) % 10;
    }


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 1000;
        int maxValue = 10000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            //int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr1 = AlgorithmUtil.generatorRandomArr(maxSize,0, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }

}
