import java.util.*;
import java.io.*;

class Santa {
    int r, c, score, stop;
    boolean alive;

    Santa(int r, int c) {
        this.r = r;
        this.c = c;
        this.score = 0;
        this.stop = 0;
        this.alive = true;
    }
}
class Rudolph {
    int r, c;

    Rudolph(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int[] dr = {-1, 0, 1, 0, -1, 1, 1, -1}, dc = {0, 1, 0, -1, 1, 1, -1, -1};

    static int N, M, P, C, D;
    static Rudolph rudolph;
    static int[][] map;
    static Santa[] santas;

    static int getDist(int r1, int c1, int r2, int c2) {
        return (int) Math.pow(r1 - r2, 2) + (int) Math.pow(c1 - c2, 2);
    }
    
    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void crash(int cr, int cc, int di, int dist, int santaId, int stop) {
        santas[santaId].score += dist;
        int nr = cr + dist * dr[di], nc = cc + dist * dc[di];
        santas[santaId].r = nr;
        santas[santaId].c = nc;
        if(!isIn(nr, nc)) {
            santas[santaId].alive = false;
            return;
        }
        santas[santaId].stop = stop;
        if(map[nr][nc] > 0) {
            interaction(nr, nc, di);
        }
        map[nr][nc] = santaId;
    }

    static void interaction(int r, int c, int di) {
        int nr = r + dr[di], nc = c + dc[di];
        santas[map[r][c]].r = nr;
        santas[map[r][c]].c = nc;
        if(!isIn(nr, nc)) {
            santas[map[r][c]].alive = false;
            return;
        }
        if(map[nr][nc] > 0) {
            interaction(nr, nc, di);
        }
        map[nr][nc] = map[r][c];
    }

    static void rudolphMove() {
        Queue<Rudolph> pq = new PriorityQueue<>((a, b) -> {
            int aDist = getDist(a.r, a.c, rudolph.r, rudolph.c), bDist = getDist(b.r, b.c, rudolph.r, rudolph.c);
            if(aDist == bDist) {
                if(a.r == b.r) {
                    return (-1) * Integer.compare(a.c, b.c);
                }
                return (-1) * Integer.compare(a.r, b.r);
            }
            return Integer.compare(aDist, bDist);
        });

        int tr = 0, tc = 0;
        boolean[][] visited = new boolean[N][N];
        pq.offer(rudolph);
        while(!pq.isEmpty()) {
            Rudolph rdp = pq.poll();
            if(visited[rdp.r][rdp.c]) {
                continue;
            }
            if(map[rdp.r][rdp.c] > 0) {
                tr = rdp.r;
                tc = rdp.c;
                break;
            }
            visited[rdp.r][rdp.c] = true;
            for(int i = 0; i < 4; i++) {
                int nr = rdp.r + dr[i], nc = rdp.c + dc[i];
                if(isIn(nr, nc) && !visited[nr][nc]) {
                    pq.offer(new Rudolph(nr, nc));
                }
            }
        }
        int ti = 0, minDist = Integer.MAX_VALUE;
        for(int i = 0; i < 8; i++) {
            int nr = rudolph.r + dr[i], nc = rudolph.c + dc[i];
            if(isIn(nr, nc)) {
                int dist = getDist(tr, tc, nr, nc);
                if(dist < minDist) {
                    minDist = dist;
                    ti = i;
                }
            }
        }
        int nr = rudolph.r + dr[ti], nc = rudolph.c + dc[ti];
        map[rudolph.r][rudolph.c] = 0;
        rudolph.r = nr;
        rudolph.c = nc;
        if(map[rudolph.r][rudolph.c] > 0) {
            crash(rudolph.r, rudolph.c, ti, C, map[rudolph.r][rudolph.c], 2);
        }
        map[rudolph.r][rudolph.c] = -1;
    }

    static void santaMove() {
        for(int id = 1; id <= P; id++) {
            if(!santas[id].alive) {
                continue;
            }
            if(santas[id].stop > 0) {
                santas[id].stop--;
                continue;
            }
            int ti = -1, minDist = Integer.MAX_VALUE;
            for(int i = 0; i < 4; i++) {
                int nr = santas[id].r + dr[i], nc = santas[id].c + dc[i], dist = getDist(nr, nc, rudolph.r, rudolph.c);
                if(isIn(nr, nc) && map[nr][nc] <= 0 && dist < minDist) {
                    ti = i;
                    minDist = dist;
                }
            }
            if(ti == -1 || minDist >= getDist(santas[id].r, santas[id].c, rudolph.r, rudolph.c)) {
                continue;
            }
            int nr = santas[id].r + dr[ti], nc = santas[id].c + dc[ti];
            map[santas[id].r][santas[id].c] = 0;
            santas[id].r = nr;
            santas[id].c = nc;
            if(map[nr][nc] == -1) {
                crash(nr, nc, (ti + 2) % 4, D, id, 1);
            } else {
                map[nr][nc] = id;
            }
        }
    }

    static boolean aliveSantas() {
        boolean result = false;
        for(int i = 1; i <= P; i++) {
            if(santas[i].alive) {
                santas[i].score += 1;
                result = true;
            }
        }
        return result;
    }

    static void solution() {
        for(int turn = 0; turn < M; turn++) {
            rudolphMove();
            santaMove();
            if(!aliveSantas()) {
                break;
            }
        }
        for(int i = 1; i <= P; i++) {
            output.append(santas[i].score).append(" ");
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        santas = new Santa[P + 1];
        st = new StringTokenizer(input.readLine());
        rudolph = new Rudolph(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        map[rudolph.r][rudolph.c] = -1;
        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(input.readLine());
            int p = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken()) - 1;
            santas[p] = new Santa(r, c);
            map[r][c] = p;
        }

        solution();

        System.out.println(output);
    }
}
