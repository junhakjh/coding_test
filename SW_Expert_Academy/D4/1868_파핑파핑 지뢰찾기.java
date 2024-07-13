import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder output = new StringBuilder();
    private static StringTokenizer st;

    private static final int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    private static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int answer = 0;
            int n = Integer.parseInt(br.readLine());
            ArrayList<StringBuilder> matrix = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder(br.readLine());
                matrix.add(sb);
            }
            ArrayList<ArrayList<Integer>> info = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                info.add(new ArrayList<>(Collections.nCopies(n, 0)));
            }
            for(int y = 0; y < n; y++) {
                for(int x = 0; x < n; x++) {
                    if(matrix.get(y).charAt(x) == '*') {
                        info.get(y).set(x, -1);
                        continue;
                    }
                    answer++;
                    int bombs = 0;
                    for(int i = 0; i < 8; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if(matrix.get(ny).charAt(nx) == '*') bombs++;
                    }
                    info.get(y).set(x, bombs);
                }
            }

            Queue<ArrayList<Integer>> q = new LinkedList<>();
            boolean[][] visited = new boolean[n][n];
            for(int y = 0; y < n; y ++) {
                for(int x = 0; x < n; x++) {
                    visited[y][x] = false;
                }
            }

            for(int y = 0; y < n; y++) {
                for(int x = 0; x < n; x++) {
                    if(info.get(y).get(x) == 0 && !visited[y][x]) {
                        visited[y][x] = true;
                        q.offer(new ArrayList<>(Arrays.asList(x, y)));
                        while(!q.isEmpty()) {
                            ArrayList<Integer> item = q.poll();
                            int curX = item.get(0); int curY = item.get(1);
                            for(int i = 0; i < 4; i++) {
                                int nx = curX + dx[i * 2]; int ny = curY + dy[i * 2];
                                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[ny][nx]) continue;
                                visited[ny][nx] = true;
                                answer--;
                                if(info.get(ny).get(nx) == 0) {
                                    q.offer(new ArrayList<>(Arrays.asList(nx, ny)));
                                }
                            }
                            for(int i = 0; i < 4; i++) {
                                int nx = curX + dx[i * 2 + 1]; int ny = curY + dy[i * 2 + 1];
                                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[ny][nx]) continue;
                                if(info.get(ny).get(nx) == 0) {
                                    visited[ny][nx] = true;
                                    q.offer(new ArrayList<>(Arrays.asList(nx, ny)));
                                    answer--;
                                } else {
                                    visited[ny][nx] = true;
                                    answer--;
                                }
                            }
                        }
                    }
                }
            }

            output.append("#").append(tc).append(" ").append(answer)
                    .append("\n");
        }

        System.out.println(output.toString());
    }
}
