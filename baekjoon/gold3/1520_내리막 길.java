import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static int R, C;
    static int[][] map, numMap;
    static PriorityQueue<int[]> pq;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static int solution() {
        int answer = 0;

        while (!pq.isEmpty()) {
            int[] item = pq.poll();
            int r = item[0], c = item[1];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (isIn(nr, nc) && map[nr][nc] > map[r][c]) {
                    numMap[r][c] += numMap[nr][nc];
                }
            }
        }

        answer = numMap[R - 1][C - 1];

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt((st.nextToken()));
        map = new int[R][C];
        numMap = new int[R][C];
        numMap[0][0] = 1;
        pq = new PriorityQueue<>((a, b) -> (-1) * Integer.compare(map[a[0]][a[1]], map[b[0]][b[1]]));
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                pq.offer(new int[]{r, c});
            }
        }

        System.out.println(solution());
    }
}
