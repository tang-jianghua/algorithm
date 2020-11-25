package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 题目描述
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 * <p>
 * 输入：
 * <p>
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
 * <p>
 * 坐标之间以;分隔。
 * <p>
 * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
 * <p>
 * 下面是一个简单的例子 如：
 * <p>
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 * <p>
 * 处理过程：
 * <p>
 * 起点（0,0）
 * <p>
 * +   A10   =  （-10,0）
 * <p>
 * +   S20   =  (-10,-20)
 * <p>
 * +   W10  =  (-10,-10)
 * <p>
 * +   D30  =  (20,-10)
 * <p>
 * +   x    =  无效
 * <p>
 * +   A1A   =  无效
 * <p>
 * +   B10A11   =  无效
 * <p>
 * +  一个空 不影响
 * <p>
 * +   A10  =  (10,-10)
 * <p>
 * 结果 （10， -10）
 * <p>
 * 注意请处理多组输入输出
 * <p>
 * 输入描述:
 * 一行字符串
 * <p>
 * 输出描述:
 * 最终坐标，以逗号分隔
 *
 * @author tangjianghua
 * @date 2020/11/19
 */
public class HJ17 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            String[] strings = s.split(";");
            int[] temp = {0, 0};
            for (int i = 0; i < strings.length; i++) {
                int[] verify = verify(strings[i]);
                if (verify != null) {
                    switch (verify[0]) {
                        case 'A':
                            temp[0] -= verify[1];
                            break;
                        case 'S':
                            temp[1] -= verify[1];
                            break;
                        case 'W':
                            temp[1] += verify[1];
                            break;
                        case 'D':
                            temp[0] += verify[1];
                            break;
                    }
                }
            }
            System.out.println(temp[0] + "," + temp[1]);
        }
    }

    private static char[] option = {'A', 'S', 'W', 'D'};


    public static boolean verifyOption(char c) {
        for (char c1 : option) {
            if (c1 == c) return true;
        }
        return false;

    }

    public static int[] verify(String s) {
        if (s.length() <= 1 || s.length() > 3) return null;
        char c = s.charAt(0);
        if (!verifyOption(c)) return null;
        String numStr = s.substring(1);
        for (int i = 0; i < numStr.length(); i++) {
            if (numStr.charAt(i) < 48 || numStr.charAt(i) > 57) return null;
        }
        return new int[]{c, Integer.parseInt(numStr)};
    }
}
