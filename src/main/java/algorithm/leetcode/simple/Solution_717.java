package main.java.algorithm.leetcode.simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
 * <p>
 * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author tangjianghua
 * date 2020/6/22
 * time 17:26
 */
public class Solution_717 {

    /**
     * 笨
     *
     * @param bits
     * @return
     */
    public static boolean isOneBitCharacter(int[] bits) {
        int a = 0;
        for (int i = 0; i < bits.length; i++) {
            a = a == 1 ? (a << 1) + bits[i] : bits[i];
        }
        return a == 0;
    }

    /**
     * 官方题解
     * 方法一：线性扫描
     * 我们可以对 \mathrm{bits}bits 数组从左到右扫描来判断最后一位是否为一比特字符。当扫描到第 ii 位时，如果 \mathrm{bits}[i]=1bits[i]=1，那么说明这是一个两比特字符，将 ii 的值增加 2。如果 \mathrm{bits}[i]=0bits[i]=0，那么说明这是一个一比特字符，将 ii 的值增加 1。
     * <p>
     * 如果 ii 最终落在了 \mathrm{bits}.\mathrm{length}-1bits.length−1 的位置，那么说明最后一位一定是一比特字符。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/solution/1bi-te-yu-2bi-te-zi-fu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param bits
     * @return
     */
    public static boolean isOneBitCharacter1(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            i += bits[i] + 1;
        }
        return i == bits.length - 1;
    }

    /**
     * 方法二：贪心
     * 三种字符分别为 0，10 和 11，那么 \mathrm{bits}bits 数组中出现的所有 0 都表示一个字符的结束位置（无论其为一比特还是两比特）。因此最后一位是否为一比特字符，只和他左侧出现的连续的 1 的个数（即它与倒数第二个 0 出现的位置之间的 1 的个数，如果 \mathrm{bits}bits 数组中只有 1 个 0，那么就是整个数组的长度减一）有关。如果 1 的个数为偶数个，那么最后一位是一比特字符，如果 1 的个数为奇数个，那么最后一位不是一比特字符。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/solution/1bi-te-yu-2bi-te-zi-fu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param bits
     * @return
     */
    public static boolean isOneBitCharacter2(int[] bits) {
        int i = bits.length - 2;
        while (i >= 0 && bits[i] == 1) {
            i--;
        }
        return (bits.length - i) % 2 == 0;
    }


    public static void main(String[] args) {
//        isOneBitCharacter1(new int[]{1, 1, 0});
        System.out.println(isOneBitCharacter2(new int[]{0, 0, 1, 1, 0}));
    }
}
