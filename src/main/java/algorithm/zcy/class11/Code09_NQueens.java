package main.java.algorithm.zcy.class11;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列，也不在同一条斜线上
 * 给定一个整数N，返回N皇后的摆法有多少种。
 * n=1，返回1
 * n=2或3, 2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 *
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code09_NQueens {

    /**
     * 主函数
     *
     * @param n n皇后
     * @return 返回所有摆法
     */
    public static int nQueens(int n) {
        if (n < 1) return 0;
        return process1(new int[n], 0, n);
    }

    /**
     * 构建递归函数
     * 传递一个i-1行已经确定的皇后占位
     * 从i行开始，从0列进行尝试，每次尝试都进行检测
     * 提供一个检测方法 validate
     * 合并所有可行的方法
     *
     * @param record 传递记录
     * @param i      下一个检测的行
     * @param n      n皇后问题
     * @return i行的可能结果
     */
    public static int process1(int[] record, int i, final int n) {
        //老规矩，先写base case
        //当i==n时，表示
        if (i == n) {
            return 1;
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            if (validate(record, i, j)) {
                record[i] = j;
                res += process1(record, i + 1, n);
            }
        }
        return res;
    }


    /**
     * 检测方法
     *
     * @param record 之前的皇后记录
     * @param i      检测的行
     * @param j      检测的列
     */
    public static boolean validate(int[] record, int i, int j) {

        //1...i-1进行检测
        for (int k = 0; k < i; k++) {
            //是否同列
            if (record[k] == j) {
                return false;
            }

            //是否同斜线
            if (Math.abs(k - i) == Math.abs(record[k] - j)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 主函数
     *
     * @param n n皇后 限制32位
     * @return 返回所有摆法
     */
    public static int nQueens2(int n) {
        if (n < 1) return 0;
        n = n >= 32 ? -1 : ((1 << n) - 1);
        return process2(n, 0, 0, 0);
    }

    /**
     * 构建递归函数
     * 优化第一个方法，使用bit代表某个n*n 位运算进行验证
     *
     * @param n           n*n皇后
     * @param columnLimit 列限制 累加
     * @param leftLimit   左斜限制 累加
     * @param rightLimit  右斜限制 累加
     * @return
     */
    public static int process2(final int n, int columnLimit, int leftLimit, int rightLimit) {
        //base case 所有的列用完了
        if (columnLimit == n) {
            return 1;
        }
        int res = 0;
        //算出可选的位置
        //columnLimit | leftLimit | rightLimit 限制的位置
        //~(columnLimit | leftLimit | rightLimit) 高位为1 的非限制位置
        //n & (~(columnLimit | leftLimit | rightLimit)) 高位去1
        int i = n & (~(columnLimit | leftLimit | rightLimit));

        //得到可选的位置后，不断地取最右侧1的数进行检测
        while (i != 0) {
            int i1 = i & (~i + 1);
            res += process2(n, columnLimit | i1,
                    (leftLimit | i1) << 1,
                    (rightLimit | i1) >>> 1
            );
            i = i ^ i1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println(nQueens(n));
            long end = System.currentTimeMillis();
            System.out.println("cost time: " + (end - start) + "ms");

        }).start();
        new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println(nQueens2(n));
            long end = System.currentTimeMillis();
            System.out.println("cost time: " + (end - start) + "ms");
        }
        ).start();
    }
}
