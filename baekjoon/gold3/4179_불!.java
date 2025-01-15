import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};

    static int R, C;
    static char[][] map;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static int solution() {
        int[][] fires = new int[R][C];
        Queue<int[]> fireQ = new ArrayDeque<>();
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                fires[r][c] = Integer.MAX_VALUE;
                if (map[r][c] == 'F') {
                    fireQ.offer(new int[]{r, c});
                    fires[r][c] = 0;
                } else if (map[r][c] == 'J') {
                    q.offer(new int[]{r, c, 0});
                    visited[r][c] = true;
                }
            }
        }
        while (!fireQ.isEmpty()) {
            int[] coor = fireQ.poll();
            int r = coor[0], c = coor[1], t = fires[r][c];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];

                if (isIn(nr, nc)) {
                    if (map[nr][nc] == '#') {
                        continue;
                    }
                    if (fires[nr][nc] > t + 1) {
                        fires[nr][nc] = t + 1;
                        fireQ.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] coor = q.poll();
            int r = coor[0], c = coor[1], t = coor[2];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (!isIn(nr, nc)) {
                    return t + 1;
                }
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == '#' || fires[nr][nc] <= t + 1) {
                        continue;
                    }
                    q.offer(new int[]{nr, nc, t + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String row = input.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = row.charAt(c);
            }
        }

        int answer = solution();

        System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);
    }
}
