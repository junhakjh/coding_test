import java.util.*;
import java.io.*;

class Twist {
    int r, c, d, v;

    Twist(int r, int c, int d, int v) {
        this.r = r;
        this.c = c;
        this.d = d;
        this.v = v;
    }
}

class Traveler {
    int r, c, t;
    boolean isTimeMap;

    Traveler(int r, int c, int t, boolean isTimeMap) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.isTimeMap = isTimeMap;
    }

    public String toString() {
        return r + ", " + c + ", " + t + ", " + isTimeMap;
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    
    static int N, M, F;
    static int sr, sc, er, ec;
    static int[][] map, timeMap;
    static int[] startPos;
    static List<Twist> twistList;
    static boolean[][][] twistMap, twistTimeMap;

    static boolean isIn(int r, int c, int K) {
        return r >= 0 && r < K && c >= 0 && c < K;
    }

    static boolean isInTimeMap(int r, int c) {
        return (r >= M && r < 2 * M) || (c >= M && c < 2 * M);
    }

    static void twistTime() {
        twistMap = new boolean[N][N][1001];
        twistTimeMap = new boolean[3 * M][3 * M][1001];
        for(Twist twist: twistList) {
            int t = 0, r = twist.r, c = twist.c, d = twist.d, v = twist.v;
            boolean isTimeMap = false;
            while(t <= 1000) {
                for(int i = t; i <= 1000; i++) {
                    if(isTimeMap) {
                        twistTimeMap[r][c][i] = true;
                    } else {
                        twistMap[r][c][i] = true;
                    }
                }

                t++;
                if(t % v != 0) {
                    continue;
                }
                r += dr[d];
                c += dc[d];
                if(!isIn(r, c, isTimeMap ? 3 * M : N) || map[r][c] == 1 || map[r][c] == 4) {
                    break;
                }
                if(!isTimeMap && map[r][c] == 3) {
                    isTimeMap = true;
                    if(d == 0 || d == 1) {
                        r = M + (r - sr);
                        c = d == 0 ? 0 : 3 * M - 1;
                    }
                    if(d == 2 || d == 3) {
                        r = d == 2 ? 0 : 3 * M - 1;
                        c = M + (c - sc);
                    }
                }
            }
        }
    }

    static int solution() {
        int answer = -1;

        twistTime();
        
        Queue<Traveler> q = new ArrayDeque<>();
        q.offer(new Traveler(startPos[0], startPos[1], 0, true));
        boolean[][] visited = new boolean[N][N], timeVisited = new boolean[3 * M][3 * M];
        timeVisited[startPos[0]][startPos[1]] = true;
        while(!q.isEmpty()) {
            Traveler tvlr = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = tvlr.r + dr[i], nc = tvlr.c + dc[i];
                // 시간의 벽 안에서 움직이는 경우
                if(tvlr.isTimeMap && isIn(nr, nc, 3 * M)) {
                    // 시간의 벽 좌표 변환
                    if(!isInTimeMap(nr, nc)) {
                        if(i == 0) {
                            if(nr < M) {
                                nr = M;
                                nc = (3 * M - 1) - tvlr.c;
                            } else if(nr >= 2 * M) {
                                nr = 2 * M - 1;
                                nc = tvlr.r;
                            }
                        }
                        if(i == 1) {
                            if(nr < M) {
                                nr = M;
                                nc = tvlr.r;
                            } else if(nr >= 2 * M) {
                                nr = 2 * M - 1;
                                nc = (3 * M - 1) - tvlr.r;
                            }
                        }
                        if(i == 2) {
                            if(nc < M) {
                                nr = (3 * M - 1) - tvlr.c;
                                nc = M;
                            } else if(nc >= 2 * M) {
                                nr = tvlr.c;
                                nc = 2 * M - 1;
                            }
                        }
                        if(i == 3) {
                            if(nc < M) {
                                nr = tvlr.c;
                                nc = M;
                            } else if(nc >= 2 * M) {
                                nr = (3 * M - 1) - tvlr.c;
                                nc = 2 * M - 1;
                            }
                        }
                    }
                    // 장애물 || 시간 이상 || 이미 방문
                    if(timeMap[nr][nc] == 1 || twistTimeMap[nr][nc][tvlr.t + 1] || timeVisited[nr][nc]) {
                        continue;
                    }
                    timeVisited[nr][nc] = true;
                    q.offer(new Traveler(nr, nc, tvlr.t + 1, true));
                    continue;
                }
                // 시간의 벽에서 미지의 공간으로 내려오는 경우
                if(tvlr.isTimeMap && !isIn(nr, nc, 3 * M)) {
                    if(i == 0 || i == 1) {
                        nr = sr + (nr - M);
                        nc = i == 0 ? ec + 1 : sc - 1;
                    }
                    if(i == 2 || i == 3) {
                        nr = i == 2 ? er + 1 : sr - 1;
                        nc = sc + (nc - M);
                    }
                }
                // 맵 밖으로 나가는 경우
                if(!isIn(nr, nc, N)) {
                    continue;
                }
                // 탈출해서 게임 종료
                if(map[nr][nc] == 4) {
                    return tvlr.t + 1;
                }
                // 장애물 || 시간 이상 || 이미 방문 || 시간의 벽
                if(map[nr][nc] == 1 || twistMap[nr][nc][tvlr.t + 1] || visited[nr][nc] || map[nr][nc] == 3) {
                    continue;
                }
                visited[nr][nc] = true;
                q.offer(new Traveler(nr, nc, tvlr.t + 1, false));
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        sr = N;
        sc = N;
        er = 0;
        ec = 0;
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 3) {
                    sr = Math.min(sr, r);
                    sc = Math.min(sc, c);
                    er = Math.max(er, r);
                    ec = Math.max(ec, c);
                }
            }
        }
        timeMap = new int[3 * M][3 * M];
        for(int c = 2 * M; c < 3 * M; c++) {
            st = new StringTokenizer(input.readLine());
            for(int r = 2 * M - 1; r >= M ; r--) {
                timeMap[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for(int c = M - 1; c >= 0; c--) {
            st = new StringTokenizer(input.readLine());
            for(int r = M; r < 2 * M ; r++) {
                timeMap[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for(int r = 2 * M; r < 3 * M ; r++) {
            st = new StringTokenizer(input.readLine());
            for(int c = M; c < 2 * M; c++) {
                timeMap[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for(int r = M - 1; r >= 0; r--) {
            st = new StringTokenizer(input.readLine());
            for(int c = 2 * M - 1; c >= M; c--) {
                timeMap[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for(int r = M; r < 2 * M ; r++) {
            st = new StringTokenizer(input.readLine());
            for(int c = M; c < 2 * M; c++) {
                timeMap[r][c] = Integer.parseInt(st.nextToken());
                if(timeMap[r][c] == 2) {
                    startPos = new int[] {r, c};
                }
            }
        }

        twistList = new ArrayList<>();
        for(int i = 0; i < F; i++) {
            st = new StringTokenizer(input.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            twistList.add(new Twist(r, c, d, v));
        }

        System.out.println(solution());
    }
}
