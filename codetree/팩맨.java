import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, -1, -1, -1, 0, 1, 1, 1};

    static int m, t;
    static int[] pacman;
    static int[][][] monsters, eggs;
    static int[][] deadMonster;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }

    static void duplicateAndMoveMonster() {
        int[][][] newMonsters = new int[4][4][8];
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 4; c++) {
                label: for(int d = 0; d < 8; d++) {
                    eggs[r][c][d] += monsters[r][c][d];
                    for(int di = 0; di < 8; di++) {
                        int nd = (d + di) % 8;
                        int nr = r + dr[nd], nc = c + dc[nd];
                        if(isIn(nr, nc) && !(pacman[0] == nr && pacman[1] == nc) && deadMonster[nr][nc] == 0) {
                            newMonsters[nr][nc][nd] += monsters[r][c][d];
                            continue label;
                        }
                    }
                    newMonsters[r][c][d] += monsters[r][c][d];
                }
            }
        }
        monsters = newMonsters;
    }

    static int maxEat;
    static int[] maxDList;
    static void movePacman() {
        maxEat = -1;
        maxDList = new int[3];
        movePacmanDfs(pacman[0], pacman[1], 0, 0, new int[3], new int[4][4], new int[4][4][3]);
        for(int d: maxDList) {
            pacman[0] += dr[d];
            pacman[1] += dc[d];
            int r = pacman[0], c = pacman[1];
            for(int di = 0; di < 8; di++) {
                if(monsters[r][c][di] > 0) {
                    deadMonster[r][c] = 3;
                    monsters[r][c][di] = 0;
                }
            }
        }
    }
    static void movePacmanDfs(int r, int c, int cnt, int ate, int[] dList, int[][] visited, int[][][] dp) {
        if(cnt == 3) {
            if(ate > maxEat) {
                maxEat = ate;
                maxDList[0] = dList[0];
                maxDList[1] = dList[1];
                maxDList[2] = dList[2];
            }
            return;
        }

        for(int i = 0; i < 8; i += 2) {
            int nr = r + dr[i], nc = c + dc[i];
            if(isIn(nr, nc)) {
                dList[cnt] = i;
                int nextAte = ate;
                if(visited[nr][nc] == 0) {
                    for(int di = 0; di < 8; di++) {
                        nextAte += monsters[nr][nc][di];
                    }
                }
                if(dp[nr][nc][cnt] == 0 || dp[nr][nc][cnt] < nextAte) {
                    dp[nr][nc][cnt] = nextAte;
                    visited[nr][nc]++;
                    movePacmanDfs(nr, nc, cnt + 1, nextAte, dList, visited, dp);
                    visited[nr][nc]--;
                }
            }
        }
    }

    static void removeDeadMonster() {
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 4; c++) {
                deadMonster[r][c] = Math.max(0, deadMonster[r][c] - 1);
            }
        }
    }

    static void completeDuplicate() {
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 4; c++) {
                for(int d = 0; d < 8; d++) {
                    monsters[r][c][d] += eggs[r][c][d];
                    eggs[r][c][d] = 0;
                }
            }
        }
    }

    static int solution() {
        int answer = 0;

        eggs = new int[4][4][16];
        deadMonster = new int[4][4];

        while(t-- > 0) {
            duplicateAndMoveMonster();
            movePacman();
            removeDeadMonster();
            completeDuplicate();
        }

        for(int[][] row: monsters) {
            for(int[] cell: row) {
                for(int num: cell) {
                    answer += num;
                }
            }
        }

        return answer;
    }
    
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        monsters = new int[4][4][8];
        st = new StringTokenizer(input.readLine());
        pacman = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        for(int id = 0; id < m; id++) {
            st = new StringTokenizer(input.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken()) - 1, d = Integer.parseInt(st.nextToken()) - 1;
            monsters[r][c][d]++;
        }

        System.out.println(solution());
    }
}
