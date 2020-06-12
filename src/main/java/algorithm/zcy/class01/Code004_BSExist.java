package main.java.algorithm.zcy.class01;

/**
 * 认识二分法
 * 1）在一个有序数组中，找某个数是否存在
 * T(N)=O(logn)
 * @author tangjianghua
 * date 2020/6/12
 * time 11:33
 */
public class Code004_BSExist {

    /**
     * 1）在一个有序数组中，找某个数是否存在
     */
    public static boolean exist(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        //递归算法
//        return exist(arr,0,arr.length-1,num);

        //非递归算法
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (r >= l) {
            //二分法查找
            mid = l + ((r - l) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    /**
     * 递归二分查找
     *
     * @param arr
     * @param l
     * @param r
     * @param num
     * @return
     */
    public static boolean exist(int[] arr, int l, int r, int num) {
        if (l > r || l > arr.length - 1 || r < 0) {
            return false;
        }
        if (l == r) {
            return arr[l] == num;
        }
        //二分法查找
        int mid = l + ((r - l) >> 1);
        if (arr[mid] == num) {
            return true;
        } else if (arr[mid] > num) {
            return exist(arr, l, mid - 1, num);
        } else {
            return exist(arr, mid + 1, r, num);
        }
    }

}
