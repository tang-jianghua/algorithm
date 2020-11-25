package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 *
 * @author tangjianghua
 * @date 2020/11/17
 */
public class HJ15 {

    public static void m1() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int i = Integer.parseInt(s);
        int total = 0;
        do {
            total += i & 1;
            i = i >>> 1;
        } while (i > 0);
        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {

        byte[] bytes = new byte[String.valueOf(Integer.MAX_VALUE).getBytes().length];
        InputStream in = System.in;
        byte b;
        int index = 0;
        while ((b = (byte) in.read()) != -1 && b != "\n".getBytes()[0]) {
            bytes[index++] = b;
        }
        int i = Integer.valueOf(new String(bytes, 0, index));
        int total = 0;
        do {
            total += i & 1;
            i = i >>> 1;
        } while (i > 0);
        System.out.println(total);
    }
}
