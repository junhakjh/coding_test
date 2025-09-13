import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    static int R, C, K;
    static int[][] infos, map;

    static boolean isIn(int r, int c) {
        return r < R && c >= 0 && c < C;
    }
    static boolean isIn(int r, int c, boolean b) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static boolean checkDown(int r, int c) {
        int nr1 = r + 1, nr2 = r + 2, nr3 = r + 1, nc1 = c - 1, nc2 = c, nc3 = c + 1;
        return isIn(nr1, nc1) && (nr1 < 0 || map[nr1][nc1] == 0) && isIn(nr2, nc2) && map[nr2][nc2] == 0 && isIn(nr3, nc3) && (nr3 < 0 || map[nr3][nc3] == 0);
    }
    static boolean checkLeft(int r, int c) {
        int nr1 = r - 1, nr2 = r, nr3 = r + 1, nc1 = c - 1, nc2 = c - 2, nc3 = c - 1;
        return isIn(nr1, nc1) && (nr1 < 0 || map[nr1][nc1] == 0) && isIn(nr2, nc2) && (nr2 < 0 || map[nr2][nc2] == 0) && isIn(nr3, nc3) && (nr3 < 0 || map[nr3][nc3] == 0);
    }
    static boolean checkRight(int r, int c) {
        int nr1 = r - 1, nr2 = r, nr3 = r + 1, nc1 = c + 1, nc2 = c + 2, nc3 = c + 1;
        return isIn(nr1, nc1) && (nr1 < 0 || map[nr1][nc1] == 0) && isIn(nr2, nc2) && (nr2 < 0 || map[nr2][nc2] == 0) && isIn(nr3, nc3) && (nr3 < 0 || map[nr3][nc3] == 0);
    }

    static void paintMap(int r, int c, int di, int id) {
        map[r][c] = id;
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            map[nr][nc] = i == di ? (-1) * id : id;
        }
    }

    static int solution() {
        int answer = 0;

        for(int i = 1; i <= K; i++) {
            int r = -2, c = infos[i][0], di = infos[i][1];
            while(true) {
                if(checkDown(r, c)) {
                    r++;
                    continue;
                }
                if(checkLeft(r, c) && checkDown(r, c - 1)) {
                    r++;
                    c--;
                    di = ((di - 1) + 4) % 4;
                    continue;
                }
                if(checkRight(r, c) && checkDown(r, c + 1)) {
                    r++;
                    c++;
                    di = (di + 1) % 4;
                    continue;
                }
                break;
            }
            if(r <= 0) {
                map = new int[R][C];
                continue;
            }
            paintMap(r, c, di, i);
            int maxR = 0;
            boolean[][] visited = new boolean[R][C];
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {r, c, i});
            while(!q.isEmpty()) {
                int[] item = q.poll();
                maxR = Math.max(maxR, item[0]);

                for(int j = 0; j < 4; j++) {
                    int nr = item[0] + dr[j], nc = item[1] + dc[j];
                    if(isIn(nr, nc, true) && !visited[nr][nc]) {
                        if((item[2] < 0 && map[nr][nc] != 0) || (item[2] > 0 && Math.abs(map[nr][nc]) == item[2])) {
                            visited[nr][nc] = true;
                            q.offer(new int[] {nr, nc, map[nr][nc]});
                        }
                    }
                }
            }
            answer += maxR + 1;
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        infos = new int[K + 1][2];
        map = new int[R][C];
        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(input.readLine());
            infos[i] = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())};
        }

        System.out.println(solution());
    }
}
