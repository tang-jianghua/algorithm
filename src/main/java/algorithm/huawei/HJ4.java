package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

/**
 * 题目描述
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * <p>
 * 输入描述:
 * 连续输入字符串(输入多次,每个字符串长度小于100)
 * <p>
 * 输出描述:
 * 输出到长度为8的新字符串数组
 *
 * @author tangjianghua
 * @date 2020/11/18
 */
public class HJ4 {

    public static void m1() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            if (s.length() < 8) {
                s += "00000000";
                System.out.println(s.substring(0, 8));
                continue;
            }
            if (s.length() == 8) {
                System.out.println(s);
                continue;
            }

            char[] chars = s.toCharArray();
            int i;
            for (i = 1; i <= chars.length; i++) {
                if (i < chars.length && i % 8 == 0) {
                    System.out.println(chars[i - 1]);
                } else {
                    System.out.print(chars[i - 1]);
                }
            }
            i--;
            if ((i = i % 8) > 0) {
                for (int j = 0; j < 8 - i; j++) {
                    System.out.print("0");
                }
                System.out.println();
            }
        }
    }

    public static void m2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            if(s.trim().length()==0){
                continue;
            }
            s=s.trim();
            if (s.length() == 8) {
                System.out.println(s);
                continue;
            }

            if (s.length() < 8) {
                StringBuilder stringBuilder = new StringBuilder(s);
                for (int i = 0; i < 8 - s.length(); i++) {
                    stringBuilder.append("0");
                }
                System.out.println(stringBuilder);
                continue;
            }


            StringBuilder stringBuilder = new StringBuilder(s);
            while (stringBuilder.length()>8){
                System.out.println(stringBuilder.substring(0,8));
                stringBuilder.delete(0,8);
            }
            if(stringBuilder.length()>0){
                int length = stringBuilder.length();
                for (int i = 0; i < 8 - length; i++) {
                    stringBuilder.append("0");
                }
                System.out.println(stringBuilder);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        m2();
    }
}
