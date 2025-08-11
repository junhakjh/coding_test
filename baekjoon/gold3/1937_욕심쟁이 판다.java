import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static int N;
    static int[][] initMap, numMap;
    static Queue<int[]> pq;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static int solution() {
        int answer = 0;

        while (!pq.isEmpty()) {
            int[] item = pq.poll();
            int height = item[0], r = item[1], c = item[2], num = numMap[r][c];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (isIn(nr, nc) && height < initMap[nr][nc] && numMap[nr][nc] < num + 1) {
                    numMap[nr][nc] = num + 1;
                    pq.offer(new int[]{initMap[nr][nc], nr, nc});
                    answer = Math.max(answer, num + 1);
                }
            }
        }

        return answer + 1;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        initMap = new int[N][N];
        numMap = new int[N][N];
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());
                initMap[r][c] = num;
                pq.offer(new int[]{num, r, c});
            }
        }

        System.out.println(solution());
    }
}
