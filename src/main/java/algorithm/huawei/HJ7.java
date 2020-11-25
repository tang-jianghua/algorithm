package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

/**
 *
 * 入门级
 *
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 *
 *
 * @author tangjianghua
 * @date 2020/11/17
 */
public class HJ7 {

    public static void m1() throws IOException {
        //首先准备一个可以存放最大浮点值的ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(String.valueOf(Float.MAX_VALUE).getBytes().length);
        byte j = "5".getBytes()[0];
        InputStream in = System.in;
        byte i ;
        boolean flag=false;
        //读取字节
        while ((i= (byte)in.read())!=-1){
            //如果读取到小数点 判断下一个是否>=5 然后跳出
            if(i=='.'){
                flag=(in.read()>=j);
                break;
            }else{
                byteBuffer.put(i);
            }
        }
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        //最后将读到的整数位转为整数
        int i1 = Integer.parseInt(new String(bytes));
        System.out.println(flag?++i1:i1);
    }

    public static void m2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String[] split = s.split("\\.");
        int i = Integer.parseInt(split[0]);
        int i1 = Integer.parseInt(split[1].substring(0, 1));
        i += i1>=5?1:0;
        System.out.println(i);
    }
    public static void m3() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        System.out.println(Math.round(Float.valueOf(s)));
    }

    public static void main(String[] args) throws IOException {
        m2();
    }
}
