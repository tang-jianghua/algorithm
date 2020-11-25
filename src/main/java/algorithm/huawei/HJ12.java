package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
 * @author tangjianghua
 * @date 2020/11/17
 */
public class HJ12 {

    public static void m1() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        char[] chars = s.toCharArray();
        for (int i = chars.length-1; i >=0; i--) {
            System.out.print(chars[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        m1();
    }
}
