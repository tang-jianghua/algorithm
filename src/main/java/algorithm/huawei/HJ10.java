package main.java.algorithm.huawei;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

/**
 * 题目描述
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
 * 例如，对于字符串abaca而言，有a、b、c三种不同的字符，因此输出3。
 * 输入描述:
 * 输入N个字符，字符在ACSII码范围内。
 * <p>
 * 输出描述:
 * 输出范围在(0~127)字符的个数。
 *
 * @author tangjianghua
 * @date 2020/11/19
 */
public class HJ10 {

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        char c;
        boolean[] ints = new boolean[128];
        int total = 0;
        while ((c = (char) in.read()) != -1) {
            if (c != '\n' && c >= 0 && c <= 127) {
                if (!ints[c]) {
                    ints[c] = true;
                    total++;
                }
            } else {
                System.out.println(total);
                for (int i = 0; i < ints.length; i++) {
                    ints[i] = false;
                }
                total = 0;
            }
        }
    }
}
