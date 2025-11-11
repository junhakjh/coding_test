import java.util.*;
import java.io.*;

class Prisoner implements Comparable<Prisoner> {
    int r, c, cost, num;

    Prisoner(int r, int c, int cost, int num) {
        this.r = r;
        this.c = c;
        this.cost = cost;
        this.num = num;
    }

    @Override
    public int compareTo(Prisoner p) {
        if (this.cost != p.cost) {
            return Integer.compare(this.cost, p.cost);
        }
        return Integer.compare(this.num, p.num);
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static int W, H;
    static int[][] prisoner;
    static char[][] map;

    static boolean isIn(int r, int c) {
        return r >= 0 && r <= H + 1 && c >= 0 && c <= W + 1;
    }

    static void solution() {
        int[][][] memo = new int[H + 2][W + 2][3];
        for (int r = 0; r <= H + 1; r++) {
            for (int c = 0; c <= W + 1; c++) {
                for (int i = 0; i < 3; i++) {
                    memo[r][c][i] = Integer.MAX_VALUE;
                }
            }
        }
        for (int p = 0; p < 3; p++) {
            ArrayDeque<int[]> dq = new ArrayDeque<>();
            dq.offerLast(new int[]{prisoner[p][0], prisoner[p][1], 0});
            while (!dq.isEmpty()) {
                int[] item = dq.poll();
                int r = item[0], c = item[1], cost = item[2];
                if (memo[r][c][p] <= cost) {
                    continue;
                }
                memo[r][c][p] = cost;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i], nc = c + dc[i];
                    if (!isIn(nr, nc) || map[nr][nc] == '*') {
                        continue;
                    }
                    int nextCost = map[nr][nc] == '#' ? cost + 1 : cost;
                    if (memo[nr][nc][p] <= nextCost) {
                        continue;
                    }
                    if (map[nr][nc] == '#') {
                        dq.offerLast(new int[]{nr, nc, nextCost});
                    } else {
                        dq.offerFirst(new int[]{nr, nc, nextCost});
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int r = 0; r <= H + 1; r++) {
            for (int c = 0; c <= W + 1; c++) {
                int cost = memo[r][c][0] + memo[r][c][1] + memo[r][c][2];
                if (map[r][c] == '#') {
                    cost -= 2;
                }
                min = Math.min(min, cost);
            }
        }

        output.append(min).append('\n');
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(input.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H + 2][W + 2];
            prisoner = new int[][]{{-1, -1}, {-1, -1}, {0, 0}};
            for (int r = 0; r <= H + 1; r++) {
                map[r][0] = '.';
                map[r][W + 1] = '.';
            }
            for (int c = 0; c <= W + 1; c++) {
                map[0][c] = '.';
                map[H + 1][c] = '.';
            }
            for (int r = 1; r <= H; r++) {
                String row = input.readLine();
                for (int c = 1; c <= W; c++) {
                    if (row.charAt(c - 1) == '$') {
                        if (prisoner[0][0] == -1) {
                            prisoner[0] = new int[]{r, c};
                        } else {
                            prisoner[1] = new int[]{r, c};
                        }
                        map[r][c] = '.';
                    } else {
                        map[r][c] = row.charAt(c - 1);
                    }
                }
            }

            solution();
        }


        System.out.print(output);
    }
}
