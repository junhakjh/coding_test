import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static int N, W;
    static int[][] infos;
    static int[][] dp;
    static int[][] dpTrace;

    static int dist(int prev, int next) {
        return Math.abs(infos[next][0] - infos[prev][0]) + Math.abs(infos[next][1] - infos[prev][1]);
    }

    static int dfs(int i1, int i2) {
        int nextI = Math.max(i1, i2) + 1;
        if (nextI == W + 2) {
            return 0;
        }
        if (dp[i1][i2] != -1) {
            return dp[i1][i2];
        }

        int dist1 = dfs(nextI, i2) + dist(i1, nextI);
        int dist2 = dfs(i1, nextI) + dist(i2, nextI);
        if (dist1 < dist2) {
            dpTrace[i1][i2] = 1;
            dp[i1][i2] = dist1;
        } else {
            dpTrace[i1][i2] = 2;
            dp[i1][i2] = dist2;
        }

        return dp[i1][i2];
    }

    static void solution() {
        output.append(dfs(0, 1)).append("\n");
        int i1 = 0, i2 = 1;
        for (int i = 2; i < W + 2; i++) {
            output.append(dpTrace[i1][i2]).append("\n");
            if (dpTrace[i1][i2] == 1) {
                i1 = i;
            } else {
                i2 = i;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(input.readLine());
        W = Integer.parseInt(st.nextToken());
        dp = new int[W + 2][W + 2];
        dpTrace = new int[W + 2][W + 2];
        for (int i = 0; i < W + 2; i++) {
            for (int j = 0; j < W + 2; j++) {
                dp[i][j] = -1;
            }
        }
        infos = new int[W + 2][2];
        infos[0] = new int[]{1, 1};
        infos[1] = new int[]{N, N};
        for (int i = 2; i < W + 2; i++) {
            st = new StringTokenizer(input.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            infos[i] = new int[]{r, c};
        }

        solution();

        System.out.println(output);
    }
}
