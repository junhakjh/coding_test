import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] coins;

    static int solution() {
        int[][] dp = new int[N + 1][M + 1];
        for (int r = 0; r <= N; r++) {
            dp[r][0] = 1;
        }
        for (int r = 1; r <= N; r++) {
            int coin = coins[r - 1];
            for (int c = 1; c <= M; c++) {
                dp[r][c] = (r - 1 < 0 ? 0 : dp[r - 1][c]) + ((c - coin) < 0 ? 0 : dp[r][c - coin]);
            }
        }

        return dp[N][M];
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(new StringTokenizer(input.readLine()).nextToken());
            coins = new int[N];
            st = new StringTokenizer(input.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(new StringTokenizer(input.readLine()).nextToken());
            output.append(solution()).append("\n");
        }


        System.out.print(output);
    }
}
