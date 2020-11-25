package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 * @author tangjianghua
 * @date 2020/11/18
 */
public class HJ11 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        char[] chars = s.toCharArray();
        for (int i = chars.length-1; i >=0; i--) {
            System.out.print(chars[i]);
        }
    }
}
