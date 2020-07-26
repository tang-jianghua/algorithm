package main.java.algorithm.zcy.class12;

/**
 * 题目一
 * 假设有排成一行的N个位置，记为1~N,N一定大于或等于2
 * 开始时机器人在其中的M位置上（M一定是1~N中的一个）
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置（P也是1~N中的一个）的方法有多少种
 * 给定四个参数N、 M、 K、 P，返回方法数。
 *
 * 暴力递归-动态规划问题
 * @auth tangjianghua
 * @date 2020/7/27
 */
public class Code01_RobotWalk {

    /**
     * 纯暴力递归解法
     * 主函数
     * @param N 总长度
     * @param M 起始位置
     * @param K 必须走K步
     * @param P 最终位置
     * @return
     */
    public static int robotWalk(int N,int M,int K,int P){
        return process1(N,P,M,K);
    }

    /**
     * 递归函数构建
     * 尝试：
     * final参数：N P 总长度和最终位置不能变
     * M 可用当前位置
     * k 可用生于步数
     */
    public static int process1(final int N,final int P,int m,int k){
        //base case
        if(m!=P&&k==0){
            return 0;
        }
        if(m==P&&k==0){
            return 1;
        }

        if(m==1){
            return process1(N,P,m+1,k-1);
        }
        if(m==N){
            return process1(N,P,m-1,k-1);
        }

        return process1(N,P,m-1,k-1)+process1(N,P,m+1,k-1);
    }

    /**
     * 暴力递归+动态规划解法
     * 主函数
     * @param N 总长度
     * @param M 起始位置
     * @param K 必须走K步
     * @param P 最终位置
     * @return
     */
    public static int robotWalkCache(int N,int M,int K,int P){
        int[][] cache=new int[N+1][K+1];
        for (int i =0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                cache[i][j]=-1;
            }
        }
        return process2(N,P,M,K,cache);
    }

    /**
     * 递归函数构建
     * 尝试：
     * final参数：N P 总长度和最终位置不能变
     * M 可用当前位置
     * k 可用生于步数
     * @param cache 重复函数的结果缓存
     */
    public static int process2(final int N, final int P, int m, int k, int[][] cache){
        if(cache[m][k]!=-1){
            return cache[m][k];
        }
        //base case
        if(m!=P&&k==0){
            cache[m][k]=0;
            return cache[m][k];
        }
        if(m==P&&k==0){
            cache[m][k]=1;
            return cache[m][k];
        }

        if(m==1){
            cache[m][k]= process1(N,P,m+1,k-1);
            return cache[m][k];
        }
        if(m==N){
            cache[m][k] = process1(N,P,m-1,k-1);
            return cache[m][k];
        }

        cache[m][k] = process1(N,P,m-1,k-1)+process1(N,P,m+1,k-1);
        return cache[m][k];
    }

    public static void main(String[] args) {
        System.out.println(robotWalk(7, 4, 9, 5));
        System.out.println(robotWalkCache(7, 4, 9, 5));
        //System.out.println(ways3(7, 4, 9, 5));
        //System.out.println(ways4(7, 4, 9, 5));
        //System.out.println(ways5(7, 4, 9, 5));
    }
}
