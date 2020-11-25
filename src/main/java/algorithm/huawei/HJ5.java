package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 题目描述
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 * <p>
 * 输入描述:
 * 输入一个十六进制的数值字符串。注意：一个用例会同时有多组输入数据，请参考帖子https://www.nowcoder.com/discuss/276处理多组输入的问题。
 * <p>
 * 输出描述:
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 *
 * @author tangjianghua
 * @date 2020/11/18
 */
public class HJ5 {

    public static void m1() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(Long.parseLong(s.substring(2), 16));
        }
    }

    //[A-Z] 65-90
    //[0-9] 48-57
    public static void m2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            String substring = s.substring(2);
            int length = substring.length();
            int radix10 = 0;
            int num = 0;
            for (int i = length - 1; i >= 0; i--) {
                char current = substring.charAt(i);
                if (current >= 65) {
                    num = current - 65 + 10;
                } else {
                    num = current - 48;
                }
                radix10 += num * Math.pow(16, length - i - 1);
            }
            System.out.println(radix10);
        }
    }

    public static void main(String[] args) throws IOException {
        m2();
    }
}
