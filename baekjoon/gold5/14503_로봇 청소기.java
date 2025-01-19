import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    static int N, M;
    static int[] coor = new int[3];
    static int[][] map;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N || c >= 0 || c < M;
    }

    static boolean isBlock(int r, int c) {
        return !isIn(r, c) || map[r][c] == 1;
    }

    static int solution() {
        int answer = 0;

        loop:
        while (answer < N * M) {
            int r = coor[0], c = coor[1], d = coor[2];
            if (map[r][c] == 0) {
                map[r][c] = -1;
                answer++;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (map[nr][nc] == 0) {
                    coor[2] -= 1;
                    if (coor[2] < 0) {
                        coor[2] += 4;
                    }
                    nr = coor[0] + dr[coor[2]];
                    nc = coor[1] + dc[coor[2]];
                    if (isIn(nr, nc) && map[nr][nc] == 0) {
                        coor[0] = nr;
                        coor[1] = nc;
                    }
                    continue loop;
                }
            }
            int nr = r + dr[(coor[2] + 2) % 4], nc = c + dc[(coor[2] + 2) % 4];
            if (isBlock(nr, nc)) {
                break;
            } else {
                coor[0] = nr;
                coor[1] = nc;
            }
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(input.readLine());
        coor[0] = Integer.parseInt(st.nextToken());
        coor[1] = Integer.parseInt(st.nextToken());
        coor[2] = Integer.parseInt(st.nextToken());
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}
