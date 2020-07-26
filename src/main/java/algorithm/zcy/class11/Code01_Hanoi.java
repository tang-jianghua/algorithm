package main.java.algorithm.zcy.class11;

/**
 * 汉诺塔问题
 * 有三个金刚石塔，第一个从小到大摞着64片黄金圆盘。现在把圆盘按大小顺序重新摆放在最后一个塔上。
 * 并且规定，在小圆盘上不能放大圆盘，在三个塔之间一次只能移动一个圆盘。
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code01_Hanoi {

    /**
     * 给出n个盘
     * @param n
     */
    public static void hanoi(int n){

        move(n,"from","to","other");
    }

    public static void move(int n,String from,String to,String other){
        if(n==0){
            return;
        }
        move(n-1,from,other,to);
        System.out.println("move "+n+" from "+from+" to "+to);
        move(n-1,other,to,from);
    }


    public static void main(String[] args) {
        hanoi(0);
    }
}
