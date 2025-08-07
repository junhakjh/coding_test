import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[][] info;

    static void solution() {
        for (int k = 0; k < N; k++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (r == c) {
                        continue;
                    }
                    // 경유가 불가능할 때
                    if (info[r][k] == 0 || info[k][c] == 0) {
                        continue;
                    }
                    // 경유가 가능하고 한 번에 못갈 때
                    if (info[r][c] == 0) {
                        info[r][c] = info[r][k] + info[k][c];
                        continue;
                    }
                    info[r][c] = Math.min(info[r][c], info[r][k] + info[k][c]);
                }
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                output.append(info[r][c]).append(" ");
            }
            output.append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        info = new int[N][N];
        st = new StringTokenizer(input.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            if (info[a][b] == 0) {
                info[a][b] = c;
                continue;
            }
            info[a][b] = Math.min(info[a][b], c);
        }

        solution();

        System.out.println(output);
    }
}
