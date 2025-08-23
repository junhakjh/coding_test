import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int N = 19;
    static int[][] map = new int[N + 12][N + 12];

    static boolean check(int r, int c) {
        int num = map[r][c];
        if (num == 0) {
            return false;
        }
        boolean right = true, rightdown = true, down = true, rightup = true;
        if (c - 1 >= 0) {
            if (map[r][c - 1] == num) {
                right = false;
            }
            if (map[r + 1][c - 1] == num) {
                rightup = false;
            }
        }
        if (r - 1 >= 0 && map[r - 1][c] == num) {
            down = false;
        }
        if (c - 1 >= 0 && r - 1 >= 0 && map[r - 1][c - 1] == num) {
            rightdown = false;
        }
        for (int i = 1; i <= 5; i++) {
            int nr = r + i, nc = c + i;
            if ((i != 5 && map[r][nc] != num) || (i == 5 && map[r][nc] == num)) {
                right = false;
            }
            if ((i != 5 && map[nr][nc] != num) || (i == 5 && map[nr][nc] == num)) {
                rightdown = false;
            }
            if ((i != 5 && map[nr][c] != num) || (i == 5 && map[nr][c] == num)) {
                down = false;
            }
            if ((i != 5 && map[r - i][nc] != num) || (i == 5 && map[r - i][nc] == num)) {
                rightup = false;
            }
        }

        return right || rightdown || down || rightup;
    }

    static void solution() {
        for (int c = 5; c < N + 5; c++) {
            for (int r = 5; r < N + 5; r++) {
                if (check(r, c)) {
                    output.append(map[r][c]).append("\n").append(r - 4).append(" ").append(c - 4);
                    return;
                }
            }
        }
        output.append(0);
    }

    public static void main(String[] args) throws Exception {
        for (int r = 5; r < N + 5; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 5; c < N + 5; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        System.out.println(output);
    }
}
