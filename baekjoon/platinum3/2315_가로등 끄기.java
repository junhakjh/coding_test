import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[][] infos;

    static int dfs(int left, int right, boolean isLeft, int[][][] dp, final int[] prefixSum) {
        if (left == 1 && right == N) {
            return 0;
        }
        if (dp[left][right][isLeft ? 0 : 1] != -1) {
            return dp[left][right][isLeft ? 0 : 1];
        }

        int min = Integer.MAX_VALUE, curIdx = isLeft ? left : right, restSum = prefixSum[N] - (prefixSum[right] - prefixSum[left - 1]);
        if (left > 1) {
            min = dfs(left - 1, right, true, dp, prefixSum) + restSum * (infos[curIdx][0] - infos[left - 1][0]);
        }
        if (right < N) {
            min = Math.min(min, dfs(left, right + 1, false, dp, prefixSum) + restSum * (infos[right + 1][0] - infos[curIdx][0]));
        }

        dp[left][right][isLeft ? 0 : 1] = min;

        return min;
    }

    static int solution() {
        int answer = 0;

        int[][][] dp = new int[N + 1][N + 1][2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = new int[]{-1, -1};
            }
        }
        int[] prefixSum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] += prefixSum[i - 1] + infos[i][1];
        }

        answer = dfs(M, M, true, dp, prefixSum);

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        infos = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());
            infos[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        System.out.print(solution());
    }
}
