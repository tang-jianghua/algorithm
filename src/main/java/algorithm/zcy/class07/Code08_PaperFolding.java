package main.java.algorithm.zcy.class07;

import main.java.algorithm.zcy.Node;

/**
 * 纸张对折问题
 * 从上往下打印折痕方向  中序遍历
 *
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code08_PaperFolding {


    /**
     * 对折N次即为N层
     * 给出一个头节点和头节点的凹凸向
     * @param n 当前层数
     * @param b ture：凹  false：凸
     * @param N 对折层数
     */
    public static void process(int n,boolean b,int N){
        if(n>N){
            return;
        }
        process(n+1,true,N);
        System.out.println(b?"凹":"凸");
        process(n+1,false,N);

    }

    public static void main(String[] args) {
        process(1,true,3);
    }


}
