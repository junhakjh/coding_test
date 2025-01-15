import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[][] desserts;

    static int solution() {
        int answer = 0;
        int[][] dp = new int[M][N];
        for(int i = 0; i < M; i++) {
            dp[i][0] = desserts[i][0];
        }

        for(int i = 1; i < N; i++) {
            for(int cur = 0; cur < M; cur++) {
                for(int prev = 0; prev < M; prev++) {
                    dp[cur][i] = Math.max(dp[cur][i], dp[prev][i - 1] + (prev == cur ? desserts[cur][i] / 2 : desserts[cur][i]));
                }
            }
        }

        for(int i = 0; i < M; i++) {
            answer = Math.max(answer, dp[i][N - 1]);
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        desserts = new int[M][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            for(int j = 0; j < N; j++) {
                desserts[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}
