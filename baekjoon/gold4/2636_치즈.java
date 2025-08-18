import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static int R, C;
    static boolean[][] map;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static void solution() {
        int time = 0, prevNum = 0;
        Queue<int[]> q = new ArrayDeque<>(), cheeseQ = new ArrayDeque<>();
        while (true) {
            time++;
            q.offer(new int[]{0, 0});
            boolean[][] visited = new boolean[R][C];
            visited[0][0] = true;
            while (!q.isEmpty()) {
                int[] item = q.poll();
                int r = item[0], c = item[1];
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i], nc = c + dc[i];
                    if (isIn(nr, nc) && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        if (map[nr][nc]) {
                            cheeseQ.offer(new int[]{nr, nc});
                        } else {
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
            if (cheeseQ.size() == 0) {
                break;
            }
            prevNum = cheeseQ.size();
            while (!cheeseQ.isEmpty()) {
                int[] item = cheeseQ.poll();
                map[item[0]][item[1]] = false;
            }
        }
        output.append(time - 1).append("\n").append(prevNum).append("\n");
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken()) != 0;
            }
        }

        solution();

        System.out.print(output);
    }
}
