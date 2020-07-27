package main.java.algorithm.zcy.class11;

/**
 * 范围上尝试的模型
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿， 但是每个玩家每次只能拿走最左或最右的纸牌， 玩家A和玩家B都绝顶聪明。
 * 请返回最后获胜者的分数。
 *
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code08_CardsInLine {

    public static int maxScore(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return Math.max(f(arr, 0, arr.length - 1), l(arr, 0, arr.length - 1));

    }

    /**
     * 先拿
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int f(int[] arr, int l, int r) {
        //base case
        if (l == r) return arr[l];

        //先拿了l
        int f = arr[l] + l(arr, l + 1, r);
        //先拿了r
        int f2 = arr[r] + l(arr, l, r - 1);

        return Math.max(f, f2);

    }

    /**
     * 后拿
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int l(int[] arr, int l, int r) {
        //base case
        if (l == r) return 0;
        //要让下一个先拿的更小
        return Math.min(f(arr, l + 1, r), f(arr, l, r - 1));
    }


    public static int dpway(int[] arr) {
        //过滤
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
            s[i][i] = 0;
        }
        for (int col = 1; col < N; col++) {
            int l=0;
            int r=col;
            while (l<N&&r<N){
                f[l][r] = Math.max(arr[l] + s[l + 1][r], arr[r] + s[l][r - 1]);
                s[l][r] = Math.min(f[l + 1][r], f[l][r - 1]);
                l++;
                r++;
            }
        }

        return Math.max(f[0][N - 1], s[0][N - 1]);

    }


    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 5, 19, 29, 80, 4};
        // A 4 9
        // B 7 5
        System.out.println(maxScore(arr));
        System.out.println(dpway(arr));
    }
}
