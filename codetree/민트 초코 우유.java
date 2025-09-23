import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static int N, T;
    static int[][] foodMap, scoreMap;

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void breakfast() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                scoreMap[r][c]++;
            }
        }
    }

    static List<int[]> lunch() {
        List<int[]> kingList = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(visited[r][c]) {
                    continue;
                }
                visited[r][c] = true;
                int max = scoreMap[r][c], food = foodMap[r][c];
                int[] king = new int[] {r, c};
                Queue<int[]> q = new ArrayDeque<>();
                List<int[]> group = new ArrayList<>();
                q.offer(new int[] {r, c});
                group.add(new int[] {r, c});
                while(!q.isEmpty()) {
                    int[] item = q.poll();
                    int cr = item[0], cc = item[1];
                    if(scoreMap[cr][cc] > max || (scoreMap[cr][cc] == max && (king[0] > cr || (king[0] == cr && king[1] > cc)))) {
                        max = scoreMap[cr][cc];
                        king = new int[] {cr, cc};
                    }
                    for(int i = 0; i < 4; i++) {
                        int nr = cr + dr[i], nc = cc + dc[i];
                        if(isIn(nr, nc) && !visited[nr][nc] && food == foodMap[nr][nc]) {
                            visited[nr][nc] = true;
                            q.offer(new int[] {nr, nc});
                            group.add(new int[] {nr, nc});
                        }
                    }
                }
                kingList.add(king);
                for(int[] coor: group) {
                    if(coor[0] == king[0] && coor[1] == king[1]) {
                        scoreMap[coor[0]][coor[1]] += (group.size() - 1);
                    } else {
                        scoreMap[coor[0]][coor[1]] -= 1;
                    }
                }
            }
        }

        return kingList;
    }

    static void dinner(List<int[]> kingList) {
        kingList.sort((a, b) -> {
            int aFood = foodMap[a[0]][a[1]], bFood = foodMap[b[0]][b[1]];
            int aSum = ((aFood & 4) / 4) + ((aFood & 2) / 2) + ((aFood & 1) / 1), bSum = ((bFood & 4) / 4) + ((bFood & 2) / 2) + ((bFood & 1) / 1);
            if(aSum == bSum) {
                if(scoreMap[a[0]][a[1]] == scoreMap[b[0]][b[1]]) {
                    if(a[0] == b[0]) {
                        return Integer.compare(a[1], b[1]);
                    }
                    return Integer.compare(a[0], b[0]);
                }
                return (-1) * Integer.compare(scoreMap[a[0]][a[1]], scoreMap[b[0]][b[1]]);
            }
            return Integer.compare(aSum, bSum);
        });
        boolean[][] visited = new boolean[N][N];
        for(int[] king: kingList) {
            int kr = king[0], kc = king[1];
            if(visited[kr][kc]) {
                continue;
            }
            int di = scoreMap[kr][kc] % 4, power = scoreMap[kr][kc] - 1, food = foodMap[kr][kc];
            scoreMap[kr][kc] = 1;
            int r = kr + dr[di], c = kc + dc[di];
            while(isIn(r, c) && power > 0) {
                if(foodMap[r][c] == food) {
                    r += dr[di];
                    c += dc[di];
                    continue;
                }
                visited[r][c] = true;
                if(power > scoreMap[r][c]) {
                    power -= (scoreMap[r][c] + 1);
                    scoreMap[r][c] += 1;
                    foodMap[r][c] = food;
                } else {
                    scoreMap[r][c] += power;
                    foodMap[r][c] = food | foodMap[r][c];
                    break;
                }

                r += dr[di];
                c += dc[di];
            }
        }
    }

    static void calculate() {
        int[] scores = new int[8];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                scores[foodMap[r][c]] += scoreMap[r][c];
            }
        }
        output.append(scores[7]).append(" ").append(scores[3]).append(" ").append(scores[5]).append(" ").append(scores[6]).append(" ").append(scores[4]).append(" ").append(scores[2]).append(" ").append(scores[1]).append("\n");
    }

    static void solution() {
        for(int tc = 0; tc < T; tc++) {
            breakfast();
            List<int[]> kingList = lunch();
            dinner(kingList);
            calculate();
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        foodMap = new int[N][N];
        scoreMap = new int[N][N];
        for(int r = 0; r < N; r++) {
            String str = input.readLine();
            for(int c = 0; c < N; c++) {
                char a = str.charAt(c);
                foodMap[r][c] = a == 'T' ? 1 : a == 'C' ? 2 : 4;
            }
        }
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for(int c = 0; c < N; c++) {
                scoreMap[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        System.out.print(output);
    }
}
