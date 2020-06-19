package main.java.algorithm;

/**
 * 常用算法归纳
 * @author tangjianghua
 * date 2020/6/12
 * time 11:21
 */
public class CommontAlgorithmUtil {

    /**
     * swap
     * @param i
     * @return
     */
    public static void swap(int i,int j){
        i=i^j;
        j=i^j;
        i=i^j;
    }

    /**
     * *2
     * @param i
     * @return
     */
    public static int multiply2(int i){
        return i<<1;
    }

    /**
     * *2+1
     * @param i
     * @return
     */
    public static int multiply2add1(int i){
        return i<<1|1;
    }

    /**
     * /2
     * @param i
     * @return
     */
    public static int divide2(int i){
        return i>>1;
    }


    /**
     * 找出一个数最右侧的1
     * @param i
     * @return
     */
    public static int right1(int i){
        return i&(~i+1);
    }

    public static void main(String[] args) {
        System.out.println(right1(3));
    }
}
