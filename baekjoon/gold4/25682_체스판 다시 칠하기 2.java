import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M, K;
    static boolean[][] map;
    static final boolean B = true, W = false;

    static int solution() {
        int answer = Integer.MAX_VALUE;

        int[][] bStartMap = new int[N + 1][M + 1], wStartMap = new int[N + 1][M + 1];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int bAdd = 0, wAdd = 0;
                if ((r + c) % 2 == 0) {
                    bAdd = map[r][c] == B ? 0 : 1;
                    wAdd = map[r][c] == W ? 0 : 1;
                }
                if ((r + c) % 2 == 1) {
                    bAdd = map[r][c] == W ? 0 : 1;
                    wAdd = map[r][c] == B ? 0 : 1;
                }
                bStartMap[r + 1][c + 1] = bStartMap[r][c + 1] + bStartMap[r + 1][c] - bStartMap[r][c] + bAdd;
                wStartMap[r + 1][c + 1] = wStartMap[r][c + 1] + wStartMap[r + 1][c] - wStartMap[r][c] + wAdd;
            }
        }
        for (int r = K; r <= N; r++) {
            for (int c = K; c <= M; c++) {
                answer = Math.min(answer, bStartMap[r][c] - bStartMap[r - K][c] - bStartMap[r][c - K] + bStartMap[r - K][c - K]);
                answer = Math.min(answer, wStartMap[r][c] - wStartMap[r - K][c] - wStartMap[r][c - K] + wStartMap[r - K][c - K]);
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            String str = new StringTokenizer(input.readLine()).nextToken();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) == 'B';
            }
        }

        System.out.println(solution());
    }
}
