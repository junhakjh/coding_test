import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int[] dy = {-1, 0, 0, 1};
    static final int[] dx = {0, -1, 1, 0};

    static int solution(int n, int[][] pool, int sharkY, int sharkX) {
        int distance = 0, sharkSize = 2, ateNum = 0;
        boolean[][] visited;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{distance, sharkSize, ateNum, sharkY, sharkX});
        while (!q.isEmpty()) {
            visited = new boolean[n][n];
            visited[sharkY][sharkX] = true;

            List<Integer[]> candidates = new ArrayList<>();
            int nextDistance = -1;
            while (!q.isEmpty()) {
                int[] items = q.poll();
                int curDistance = items[0];

                if(nextDistance != -1 && curDistance >= nextDistance) {
                    break;
                }


                sharkSize = items[1];
                ateNum = items[2];
                int y = items[3], x = items[4];

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i], nx = x + dx[i];
                    if (ny < 0 || ny >= n || nx < 0 || nx >= n || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    if (pool[ny][nx] > sharkSize) continue;

                    int nd = curDistance + 1;
                    if (pool[ny][nx] != 0 && pool[ny][nx] < sharkSize) {
                        nextDistance = nd;
                        candidates.add(new Integer[]{ny, nx});
                    } else {
                        q.offer(new int[]{nd, sharkSize, ateNum, ny, nx});
                    }
                }
            }
            if (candidates.size() > 0) {
                candidates.sort(((o1, o2) -> {
                    if (o1[0].equals(o2[0])) {
                        return o1[1].compareTo(o2[1]);
                    } else {
                        return o1[0].compareTo(o2[0]);
                    }
                }));
                int ny = candidates.get(0)[0], nx = candidates.get(0)[1];
                pool[ny][nx] = 0;
                if (++ateNum == sharkSize) {
                    ateNum = 0;
                    sharkSize++;
                }
                q = new ArrayDeque<>();
                q.offer(new int[]{0, sharkSize, ateNum, ny, nx});
                distance += nextDistance;
                sharkY = ny;
                sharkX = nx;
            }
        }

        return distance;
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(input.readLine());
        int[][] pool = new int[n][n];
        int sharkX = 0, sharkY = 0;
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(input.readLine());
            for (int x = 0; x < n; x++) {
                pool[y][x] = Integer.parseInt(st.nextToken());
                if (pool[y][x] == 9) {
                    sharkY = y;
                    sharkX = x;
                    pool[y][x] = 0;
                }
            }
        }
        System.out.println(solution(n, pool, sharkY, sharkX));
    }
}
