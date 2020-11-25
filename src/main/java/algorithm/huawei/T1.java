package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author tangjianghua
 * @date 2020/11/22
 */
public class T1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int start = 0;
        String s;
        while (start == 0 && (s = bufferedReader.readLine()) != null) {
            int size = Integer.parseInt(s);
            if (size < 0 || size > 59) {
                continue;
            }
            System.out.println(get(size));
        }
    }

    private static String get(int m) {
        String s = "1";
        for (int i = 1; i < m+1; i++) {
            //当前数值的字符拼接容器
            StringBuilder stringBuilder = new StringBuilder();

            //记录上一个字符
            char pre = s.charAt(0);
            //记录上一个字符出现次数
            int num = 1;

            //开始解析
            for (int j = 0; j < s.length(); j++) {
                if (j == 0) {
                    continue;
                }
                //获取当前字符
                char c = s.charAt(j);
                if (c == pre) {
                    num++;
                } else {
                    stringBuilder.append(num)
                            .append(pre);
                    pre = c;
                    num = 1;
                }
            }
            stringBuilder.append(num)
                    .append(pre);
            s = stringBuilder.toString();
        }
        return s;
    }

    private static String[] init() {
        String[] arr = new String[60];
        arr[0] = "1";
        for (int i = 1; i < arr.length; i++) {

            //获取上一个值 转化为字符串进行解析
            String s = String.valueOf(arr[i - 1]);
            //当前数值的字符拼接容器
            StringBuilder stringBuilder = new StringBuilder();

            //记录上一个字符
            char pre = s.charAt(0);
            //记录上一个字符出现次数
            int num = 1;

            //开始解析
            for (int j = 0; j < s.length(); j++) {
                if (j == 0) {
                    continue;
                }
                //获取当前字符
                char c = s.charAt(j);
                if (c == pre) {
                    num++;
                } else {
                    stringBuilder.append(num)
                            .append(pre);
                    pre = c;
                    num = 1;
                }
            }
            stringBuilder.append(num)
                    .append(pre);
            arr[i] = stringBuilder.toString();
        }
        return arr;
    }
}
