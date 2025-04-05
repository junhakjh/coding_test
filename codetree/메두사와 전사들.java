import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr1 = {-1, 1, 0, 0}, dc1 = {0, 0, -1, 1}, dr2 = {0, 0, -1, 1}, dc2 = {-1, 1, 0, 0};
    static final int[] reverseD = {1, 0, 3, 2};

    static int N, M;
    static int[] house, park, snake;
    static ArrayDeque<int[]> soldiers;
    static boolean[][] map;
    static int[][] soldierMap;
    static int[][] rockMap;
    static int[] route;
    static int totalMoves = -1;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void findRoute() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {house[0], house[1], 0});
        int[][] visited = new int[N][N];
        loop: while (!q.isEmpty()) {
            int[] pos = q.poll();
            int r = pos[0], c = pos[1], num = pos[2];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr1[i], nc = c + dc1[i];
                if (isIn(nr, nc) && map[nr][nc]) {
                    map[nr][nc] = false;
                    visited[nr][nc] = reverseD[i];
                    if(nr == park[0] && nc == park[1]) {
                        totalMoves = num + 1;
                        break loop;
                    }
                    q.offer(new int[] {nr, nc, num + 1});
                }
            }
        }
        if(totalMoves == -1) {
            return;
        }
        route = new int[totalMoves];
        int r = park[0], c = park[1];
        for (int i = totalMoves - 1; i >= 0; i--) {
            int d = visited[r][c];
            route[i] = reverseD[d];
            r = r + dr1[d];
            c = c + dc1[d];
        }
    }

    static void snakeMove(int i) {
        int d = route[i], r = snake[0] + dr1[d], c = snake[1] + dc1[d];
        snake = new int[] {r, c};
        soldierMap[r][c] = 0;
    }

    static int makeRock() {
        int maxRock = 0;
        int tempRock;
        int[][] tempRockMap1 = new int[N][N];
        tempRock = 0;
        for (int r = snake[0] - 1; r >= 0; r--) {
            int diff = snake[0] - r;
            for (int c = Math.max(snake[1] - diff, 0); c <= Math.min(snake[1] + diff, N - 1); c++) {
                if (tempRockMap1[r][c] == 2) {
                    continue;
                }
                tempRockMap1[r][c] = 1;
                if (soldierMap[r][c] > 0) {
                    tempRock += soldierMap[r][c];
                    for(int nr = r - 1; nr >= 0; nr--) {
                        tempRockMap1[nr][c] = 2;
                    }
                    if(c > snake[1]) {
                        for(int nr = r - 1; nr >= 0; nr--) {
                            int tempDiff = r - nr;
                            for(int nc = c + 1; nc <= Math.min(c + tempDiff, N - 1); nc++) {
                                tempRockMap1[nr][nc] = 2;
                            }
                        }
                    }
                    if(c < snake[1]) {
                        for(int nr = r - 1; nr >= 0; nr--) {
                            int tempDiff = r - nr;
                            for(int nc = c - 1; nc >= Math.max(c - tempDiff, 0); nc--) {
                                tempRockMap1[nr][nc] = 2;
                            }
                        }
                    }
                }
            }
        }
        if(tempRock > maxRock) {
            maxRock = tempRock;
            rockMap = tempRockMap1;
        }

        int[][] tempRockMap2 = new int[N][N];
        tempRock = 0;
        for (int r = snake[0] + 1; r < N; r++) {
            int diff = r - snake[0];
            for (int c = Math.max(snake[1] - diff, 0); c <= Math.min(snake[1] + diff, N - 1); c++) {
                if (tempRockMap2[r][c] == 2) {
                    continue;
                }
                tempRockMap2[r][c] = 1;
                if (soldierMap[r][c] > 0) {
                    tempRock += soldierMap[r][c];
                    for(int nr = r + 1; nr < N; nr++) {
                        tempRockMap2[nr][c] = 2;
                    }
                    if(c > snake[1]) {
                        for(int nr = r + 1; nr < N; nr++) {
                            int tempDiff = nr - r;
                            for(int nc = c + 1; nc <= Math.min(c + tempDiff, N - 1); nc++) {
                                tempRockMap2[nr][nc] = 2;
                            }
                        }
                    }
                    if(c < snake[1]) {
                        for(int nr = r + 1; nr < N; nr++) {
                            int tempDiff = nr - r;
                            for(int nc = c - 1; nc >= Math.max(c - tempDiff, 0); nc--) {
                                tempRockMap2[nr][nc] = 2;
                            }
                        }
                    }
                }
            }
        }
        if(tempRock > maxRock) {
            maxRock = tempRock;
            rockMap = tempRockMap2;
        }

        int[][] tempRockMap3 = new int[N][N];
        tempRock = 0;
        for (int c = snake[1] - 1; c >= 0; c--) {
            int diff = snake[1] - c;
            for (int r = Math.max(snake[0] - diff, 0); r <= Math.min(snake[0] + diff, N - 1); r++) {
                if (tempRockMap3[r][c] == 2) {
                    continue;
                }
                tempRockMap3[r][c] = 1;
                if (soldierMap[r][c] > 0) {
                    tempRock += soldierMap[r][c];
                    for(int nc = c - 1; nc >= 0; nc--) {
                        tempRockMap3[r][nc] = 2;
                    }
                    if(r < snake[0]) {
                        for(int nc = c - 1; nc >= 0; nc--) {
                            int tempDiff = c - nc;
                            for(int nr = r - 1; nr >= Math.max(r - tempDiff, 0); nr--) {
                                tempRockMap3[nr][nc] = 2;
                            }
                        }
                    }
                    if(r > snake[0]) {
                        for(int nc = c - 1; nc >= 0; nc--) {
                            int tempDiff = c - nc;
                            for(int nr = r + 1; nr <= Math.min(r + tempDiff, N - 1); nr++) {
                                tempRockMap3[nr][nc] = 2;
                            }
                        }
                    }
                }
            }
        }
        if(tempRock > maxRock) {
            maxRock = tempRock;
            rockMap = tempRockMap3;
        }

        int[][] tempRockMap4 = new int[N][N];
        tempRock = 0;
        for (int c = snake[1] + 1; c < N; c++) {
            int diff = c - snake[1];
            for (int r = Math.max(snake[0] - diff, 0); r <= Math.min(snake[0] + diff, N - 1); r++) {
                if (tempRockMap4[r][c] == 2) {
                    continue;
                }
                tempRockMap4[r][c] = 1;
                if (soldierMap[r][c] > 0) {
                    tempRock += soldierMap[r][c];
                    for(int nc = c + 1; nc < N; nc++) {
                        tempRockMap4[r][nc] = 2;
                    }
                    if(r < snake[0]) {
                        for(int nc = c + 1; nc < N; nc++) {
                            int tempDiff = nc - c;
                            for(int nr = r - 1; nr >= Math.max(r - tempDiff, 0); nr--) {
                                tempRockMap4[nr][nc] = 2;
                            }
                        }
                    }
                    if(r > snake[0]) {
                        for(int nc = c + 1; nc < N; nc++) {
                            int tempDiff = nc - c;
                            for(int nr = r + 1; nr <= Math.min(r + tempDiff, N - 1); nr++) {
                                tempRockMap4[nr][nc] = 2;
                            }
                        }
                    }
                }
            }
        }
        if(tempRock > maxRock) {
            maxRock = tempRock;
            rockMap = tempRockMap4;
        }
        
        return maxRock;
    }

    static boolean isMoreNear(int r, int c, int nr, int nc, int sr, int sc) {
        return Math.abs(sr - r) + Math.abs(sc - c) > Math.abs(sr - nr) + Math.abs(sc - nc);
    }

    static int[] moveAndAttack() {
        int moves = 0, deathNum = 0;
        int length = soldiers.size();
        loop: for(int t = 0; t < length; t++) {
            int[] soldier = soldiers.poll();
            int r = soldier[0], c = soldier[1];
            if(soldierMap[r][c] == 0) {
                continue;
            }
            if(rockMap[r][c] == 1) {
                soldiers.offer(soldier);
                continue;
            }
            for(int i = 0; i < 4; i++) {
                int nr = r + dr1[i], nc = c + dc1[i];
                if(isIn(nr, nc) && isMoreNear(r, c, nr, nc, snake[0], snake[1]) && rockMap[nr][nc] != 1) {
                    moves += 1;
                    // System.out.println(r + ", " + c + ", " + nr + ", " + nc);
                    soldierMap[r][c] -= 1;
                    r = nr;
                    c = nc;
                    if(r == snake[0] && c == snake[1]) {
                        deathNum += 1;
                        continue loop;
                    }
                    soldierMap[r][c] += 1;
                    break;
                }
            }
            for(int i = 0; i < 4; i++) {
                int nr = r + dr2[i], nc = c + dc2[i];
                if(isIn(nr, nc) && isMoreNear(r, c, nr, nc, snake[0], snake[1]) && rockMap[nr][nc] != 1) {
                    moves += 1;
                    soldierMap[r][c] -= 1;
                    r = nr;
                    c = nc;
                    if(r == snake[0] && c == snake[1]) {
                        deathNum += 1;
                        continue loop;
                    }
                    soldierMap[r][c] += 1;
                    break;
                }
            }
            soldiers.offer(new int[] {r, c});
        }

        return new int[] {moves, deathNum};
    }

    static void solution() {
        findRoute();
        if(totalMoves == -1) {
            output.append("-1");
            return;
        }
        for(int i = 0; i < route.length - 1; i++) {
            snakeMove(i);
            int rockSoldiers = makeRock();
            int[] result = moveAndAttack();
            output.append(result[0]).append(" ").append(rockSoldiers).append(" ").append(result[1]).append("\n");
        }
        output.append("0");
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(input.readLine());
        house = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        snake = house;
        park = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        soldiers = new ArrayDeque<>();
        soldierMap = new int[N][N];
        st = new StringTokenizer(input.readLine());
        for(int i = 0; i < M; i++) {
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            soldiers.offer(new int[] {r, c});
            soldierMap[r][c] += 1;
        }
        map = new boolean[N][N];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
            }
        }

        solution();

        System.out.println(output);
    }
}
