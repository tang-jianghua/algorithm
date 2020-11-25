package main.java.algorithm.huawei;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * 计算字符串最后一个单词的长度，单词以空格隔开。
 * @author tangjianghua
 * @date 2020/11/18
 */
public class HJ1 {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        byte b;
        int lenth=0;
        while((b=(byte) inputStream.read())!=-1){
            lenth++;
            if(b==" ".getBytes()[0]){
                lenth=0;
            }
            if(b=="\n".getBytes()[0]){
                System.out.println(--lenth);
                lenth=0;
            }
        }
    }
}
