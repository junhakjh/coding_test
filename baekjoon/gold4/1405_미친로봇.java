import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    static int N;
    static double[] possibilities;
    static double answer = 0.0;
    static boolean[][] visited;

    static void dfs(int depth, int r, int c, double possibility) {
        if (depth == N) {
            answer += possibility;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (visited[nr][nc] || possibilities[i] == 0) {
                continue;
            }
            visited[nr][nc] = true;
            dfs(depth + 1, nr, nc, possibility * possibilities[i]);
            visited[nr][nc] = false;
        }
    }

    static double solution() {
        visited[N][N] = true;
        dfs(0, N, N, 1.0);

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[2 * N + 2][2 * N + 2];
        possibilities = new double[]{Double.parseDouble(st.nextToken()) / 100.0, Double.parseDouble(st.nextToken()) / 100.0, Double.parseDouble(st.nextToken()) / 100.0, Double.parseDouble(st.nextToken()) / 100.0};

        System.out.println(solution());
    }
}
