import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[][] map;

    static int solution() {
        int answer = Integer.MIN_VALUE;
        int[][] sumMap = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                sumMap[r][c] = map[r - 1][c - 1] + sumMap[r - 1][c] + sumMap[r][c - 1] - sumMap[r - 1][c - 1];
            }
        }

        for (int n = 1; n <= N; n++) {
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (r - n < 0 || c - n < 0) {
                        continue;
                    }
                    int sum = sumMap[r][c] - sumMap[r -  n][c] - sumMap[r][c - n] + sumMap[r - n][c - n];
                    answer = Math.max(answer, sum);
                }
            }
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}
