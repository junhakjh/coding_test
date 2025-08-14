import java.util.*;
import java.io.*;

class Union {
    int idx, num, R, C;
    boolean[][] shape;
    
    Union(int idx, int num, int R, int C, boolean[][] shape) {
        this.idx = idx;
        this.num = num;
        this.R = R;
        this.C = C;
        this.shape = shape;
    }
}

public class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static int N, Q;
    static int[][] map;
    static int[][] infos;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static int insert(int[][] map, Union union, int startR, int startC, int[] nums) {
        if(!isIn(startR + union.R - 1, startC + union.C - 1)) return -1;
        for(int r = startR; r < startR + union.R; r++) {
            for(int c = startC; c < startC + union.C; c++) {
                if(union.shape[r - startR][c - startC] && map[r][c] != 0) {
                    return -1;
                }
            }
        }

        boolean[] isChecked = new boolean[nums.length];
        int sum = 0;
        for(int r = startR; r < startR + union.R; r++) {
            for(int c = startC; c < startC + union.C; c++) {
                if(union.shape[r - startR][c - startC]) {
                    map[r][c] = union.idx;
                    for(int i = 0; i < 4; i++) {
                        int nr = r + dr[i], nc = c + dc[i];
                        if(isIn(nr, nc) && map[nr][nc] != union.idx && !isChecked[map[nr][nc]]) {
                            isChecked[map[nr][nc]] = true;
                            sum += union.num * nums[map[nr][nc]];
                        }
                    }
                }
            }
        }
        
        return sum;
    }

    static void solution() {
        Queue<Union> pq = new PriorityQueue<>((e1, e2) -> {
            if(e1.num == e2.num) {
                return Integer.compare(e1.idx, e2.idx);
            }
            return (-1) * Integer.compare(e1.num, e2.num);
        });
        for(int unionIdx = 1; unionIdx <= Q; unionIdx++) {
            for(int r = infos[unionIdx][1]; r < infos[unionIdx][3]; r++) {
                for(int c = infos[unionIdx][0]; c < infos[unionIdx][2]; c++) {
                    map[r][c] = unionIdx;
                }
            }

            boolean[][] visited = new boolean[N][N];
            int[] isAlive = new int[unionIdx + 1];
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(map[r][c] == 0 || visited[r][c]) continue;
                    visited[r][c] = true;
                    if(isAlive[map[r][c]] < 0) continue;
                    if(isAlive[map[r][c]] > 0) {
                        isAlive[map[r][c]] = -1;
                        continue;
                    }
                    isAlive[map[r][c]] = 1;
                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {r, c});
                    visited[r][c] = true;
                    int num = 1, minR = r, maxR = r, minC = c, maxC = c;
                    while(!q.isEmpty()) {
                        int[] item = q.poll();
                        int cr = item[0], cc = item[1];
                        for(int i = 0; i < 4; i++) {
                            int nr = cr + dr[i], nc = cc + dc[i];
                            if(isIn(nr, nc) && map[r][c] == map[nr][nc] && !visited[nr][nc]) {
                                num++;
                                visited[nr][nc] = true;
                                minR = Math.min(minR, nr);
                                maxR = Math.max(maxR, nr);
                                minC = Math.min(minC, nc);
                                maxC = Math.max(maxC, nc);
                                q.offer(new int[] {nr, nc});
                            }
                        }
                    }
                    int R = maxR - minR + 1, C = maxC - minC + 1;
                    boolean[][] shape = new boolean[R][C];
                    for(int cr = minR; cr <= maxR; cr++) {
                        for(int cc = minC; cc <= maxC; cc++) {
                            if(map[cr][cc] == map[r][c]) {
                                shape[cr - minR][cc - minC] = true;
                            }
                        }
                    }
                    pq.offer(new Union(map[r][c], num, R, C, shape));
                }
            }

            map = new int[N][N];
            int[] nums = new int[unionIdx + 1];
            int sum = 0;
            while(!pq.isEmpty()) {
                Union union = pq.poll();
                if(isAlive[union.idx] < 0) continue;
                label: for(int c = 0; c < N; c++) {
                    for(int r = 0; r < N; r++) {
                        int num = insert(map, union, r, c, nums);
                        if(num != -1) {
                            sum += num;
                            nums[union.idx] = union.num;
                            break label;
                        }
                    }
                }
            }

            output.append(sum).append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        infos = new int[Q + 1][4];
        for(int i = 1; i <= Q; i++) {
            st = new StringTokenizer(input.readLine());
            infos[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        solution();

        System.out.println(output);
    }
}
