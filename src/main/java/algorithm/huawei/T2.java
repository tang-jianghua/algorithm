package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [a-z] 十进制: [97-122]
 * [A-Z] 十进制: [65-90]
 * 97-65=32
 * //[0-9] 48-57
 *
 * @author tangjianghua
 * @date 2020/11/22
 */
public class T2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int start = 0;
        String s;
        while (start == 0 && (s = bufferedReader.readLine()) != null) {

            //[全量字符集,已占用字符集]
            String[] split = s.split("@");
            //全量字符集
            String all = split[0];
            //记录所有字符  桶排
            int[] arr = new int[123];
            //["a:3","b:5","c:2"]
            String[] strings = all.split(",");
            for (int i = 0; i < strings.length; i++) {
                char c = strings[i].charAt(0);
                arr[c] += Integer.parseInt(strings[i].substring(2));
            }


            if (split.length > 1) {
                //已占用字符集
                String used = split[1];
                //["a:1","b:2"]
                String[] stringsUsed = used.split(",");
                for (int i = 0; i < stringsUsed.length; i++) {
                    char c = stringsUsed[i].charAt(0);
                    arr[c] -= Integer.parseInt(stringsUsed[i].substring(2));
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 0) {
                    stringBuilder
                            .append(",")
                            .append((char) i)
                            .append(':')
                            .append(arr[i]);
                }
            }
            System.out.println(stringBuilder.substring(1));
        }
    }


}
