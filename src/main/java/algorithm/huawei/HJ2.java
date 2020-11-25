package main.java.algorithm.huawei;

import java.io.IOException;
import java.io.InputStream;

/**
 * 题目描述
 * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 * <p>
 * 输入描述:
 * 第一行输入一个有字母和数字以及空格组成的字符串，第二行输入一个字符。
 * <p>
 * 输出描述:
 * 输出输入字符串中含有该字符的个数。
 * <p>
 * <p>
 * 思路：
 * 桶排
 * ASCII码作为下角标
 * 空格          32
 * [a-z] 十进制: [97-122]
 * [A-Z] 十进制: [65-90]
 * 97-65=32
 *
 * @author tangjianghua
 * @date 2020/11/18
 */
public class HJ2 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        //存储0-90的量
        int[] ints = new int[91];
        int i;
        //查找的字符
        int j;
        while ((i = inputStream.read()) != -1) {
            if (i == '\n') {
                //阻塞读取下一个查找的字符
                j = inputStream.read();
                //跳过下个回车符
                inputStream.read();
                //小写转大写
                if (j >= 97 && j <= 122) {
                    j -= 32;
                }
                System.out.println(ints[j]);
                //完成一次事务 重置
                ints = new int[91];
                continue;
            }
            //小写转大写
            if (i >= 97 && i <= 122) {
                i -= 32;
            }
            //记录
            ints[i]++;
        }
    }
}
