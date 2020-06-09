package main.java.algorithm;

/**
 * 在数据结构与算法实践过程中，经常会遇到利用递归实现算法的情况。递归是一种分而治之、将复杂问题转换成简单问题的求解方法。使用递归可以使编写的程序简洁、结构清晰，程序的正确性很容易证明，不需要了解递归调用的具体细节。
 * <p>
 * （1）函数的递归：就是函数自己调用自己，即一个函数在调用其他函数的过程中，又出现对自身的调用，这种函数称为递归函数。函数的递归调用就是自己调用自己，可以直接调用自己也可以间接调用。其中，在函数中直接调用自己称为函数的直接递归调用；如果函数f1调用了函数f2又再次调用了函数f1，这种调用的方式我们称之为间接递归调用。
 *
 * @author tangjianghua
 * date 2020/6/9
 * time 17:10
 */
public class Recursion {

    public static void main(String[] args) {

        System.out.println(factorial(4));

        System.out.println(findMax(new int[]{2, 6, 8, 3, 1}, 4));


    }

    /**
     * 例1：利用递归求n!
     */
    public static int factorial(int n) {
        return n <= 0 ? 1 : n * factorial(n - 1);
    }


    /**
     * 例2：已知有数组a[] ，要求利用递归实现求前n个数中的最大值。不要排序。
     */
    public static int findMax(int[] a, int n) {
        if (n <= 1) {
            return a[n-1];
        }
        int temp = findMax(a, n - 1);
        return a[n-1]>=temp?a[n-1]:temp;
    }

}
