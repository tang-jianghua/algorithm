package main.java.algorithm.zcy.class04;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * @auth tangjianghua
 * @date 2020/7/19
 */
public class Code04_HeapSort {

    /**
     * 从index开始heapify到heapSize
     * T(N)=O(logN)
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    public static void heapify(int arr[], int index, int heapSize) {
        int left;
        while ((left = index * 2 + 1) < heapSize) {
            int largiest = (left + 1) < heapSize && arr[left + 1] > arr[left] ? (left + 1) : left;
            if (arr[index] < arr[largiest]) {
                AlgorithmUtil.swapArr(arr, index, largiest);
                index = largiest;
            } else {
                break;
            }
            //AlgorithmUtil.printArr(arr);
        }
    }

    /**
     * T(N)=O(logN)
     *
     * @param arr
     * @param index
     */
    public static void heapInsert(int arr[], int index) {

        int parent;
        while ((parent = (index - 1) / 2) >= 0 && arr[index] > arr[parent]) {
            AlgorithmUtil.swapArr(arr, index, parent);
            index = parent;
        }
    }

    /**
     * O(N*logN)
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        //改为大根堆
        //O(N*logN)
      /*  for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }*/

        //改为大根堆
        //O(N)
        //犯错：i没有=0
        //犯错：heapify(arr, i, arr.length-i);
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //改为大根堆以后，采用每次pop最大根的思路，将最大根放到数组最后，不断往后排，完成堆排序
        //O(N*logN)
        int heapSize = arr.length;
        for (int i = 0; i < arr.length; i++) {
            AlgorithmUtil.swapArr(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
        //AlgorithmUtil.printArr(arr);

    }

    public static void main(String[] args) {

     /*   int maxSize=10;
        int maxValue=100;
        int[] ints = AlgorithmUtil.generatorRandomArr(maxSize, maxValue);
        AlgorithmUtil.printArr(ints);
        heapSort(ints);*/

        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = AlgorithmUtil.generatorRandomArr(maxSize, maxValue);
            int[] arr2 = AlgorithmUtil.copyArr(arr1);
            //System.out.println(AlgorithmUtil.isEqual(arr1, arr2));
            heapSort(arr1);
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
