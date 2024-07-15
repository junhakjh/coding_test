import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {1, 0, -1, 0}; static final int[] dy = {0, 1, 0, -1};
    static final int EMPTY = 0;
    static final int ISLAND = 1;
    static final int TORNADO = 2;

    static class Point implements Comparable<Point> {
        int x, y, time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            return this.time - p.time;
        }
    }

    private static int solution(int n, int[][] matrix, int[] start, int[] target) {
        boolean[][] visited = new boolean[n][n];
        Point tp = new Point(target[1], target[0]);

        Queue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(start[1], start[0], 0));

        while(!pq.isEmpty()) {
            Point p = pq.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i]; int ny = p.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(matrix[ny][nx] == ISLAND || visited[ny][nx]) continue;

                if(nx == tp.x && ny == tp.y) return p.time + 1;

                if(matrix[ny][nx] == TORNADO && p.time % 3 != 2) {
                    pq.offer(new Point(nx, ny, p.time + ((p.time % 3) == 0 ? 3 : 2)));
                }
                else {
                    pq.offer(new Point(nx, ny, p.time + 1));
                }
                visited[ny][nx] = true;
            }
        }

        return -1;
    }


    public static void main(String[] args) throws Exception {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder output = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(input.readLine());
            int[][] matrix = new int[n][n];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(input.readLine());
                int[] row = new int[n];
                int j = 0;
                while(st.hasMoreTokens()) {
                    row[j++] = Integer.parseInt(st.nextToken());
                }
                matrix[i] = row;
            }
            int[] start = {0, 0}; int[] target = {0, 0};
            st = new StringTokenizer(input.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(input.readLine());
            target[0] = Integer.parseInt(st.nextToken());
            target[1] = Integer.parseInt(st.nextToken());

            output.append("#").append(tc).append(" ").append(solution(n, matrix, start, target)).append("\n");
        }

        System.out.println(output.toString());
    }
}
