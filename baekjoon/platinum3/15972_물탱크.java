import java.util.*;
import java.io.*;

class Edge {
    int from, to, cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M, H;
    static int[][] map;
    static List<Edge>[] edgeList;

    static int solution() {
        int answer = 0;

        Queue<Edge> pq = new PriorityQueue<>((a, b) -> {
            int atr = a.to / M, atc = a.to % M, btr = b.to / M, btc = b.to % M;
            return Integer.compare(map[a.to / M][a.to % M], map[b.to / M][b.to % M]);
        });
        for (List<Edge> edges : edgeList) {
            for (Edge edge : edges) {
                int fr = edge.from / M, fc = edge.from % M, tr = edge.to / M, tc = edge.to % M;
                if (edge.cost >= map[fr][fc] || map[tr][tc] >= map[fr][fc]) {
                    continue;
                }
                pq.offer(edge);
            }
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int fr = edge.from / M, fc = edge.from % M, tr = edge.to / M, tc = edge.to % M;
            if (edge.cost >= map[fr][fc] || map[tr][tc] >= map[fr][fc]) {
                continue;
            }
            map[fr][fc] = Math.max(edge.cost, map[tr][tc]);
            for (Edge nextEdge : edgeList[edge.from]) {
                int nfr = nextEdge.to / M, nfc = nextEdge.to % M;
                // 왔던 곳일 때
                // 다음이 현재보다 낮거나 같을 때
                // 통로가 현재보다 높이 달려있을 때
                // 통로가 다음보다 높이 달려있을 때
                if (nextEdge.to == edge.to || map[fr][fc] >= map[nfr][nfc] || nextEdge.cost >= map[nfr][nfc]) {
                    continue;
                }
                pq.offer(new Edge(nextEdge.to, edge.from, nextEdge.cost));
            }
        }

        for (int[] row : map) {
            for (int h : row) {
                answer += h;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        edgeList = new ArrayList[N * M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                map[r][c] = H;
                edgeList[r * M + c] = new ArrayList<>();
            }
        }
        for (int r = -1; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < M; c++) {
                int h = Integer.parseInt(st.nextToken());
                if (h == -1) {
                    continue;
                }
                if (r == -1 || r == N - 1) {
                    int cr = Math.max(0, r);
                    map[cr][c] = Math.min(map[cr][c], h);
                    continue;
                }
                int a = r * M + c, b = (r + 1) * M + c;
                edgeList[a].add(new Edge(a, b, h));
                edgeList[b].add(new Edge(b, a, h));
            }
        }
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = -1; c < M; c++) {
                int h = Integer.parseInt(st.nextToken());
                if (h == -1) {
                    continue;
                }
                if (c == -1 || c == M - 1) {
                    int cc = Math.max(0, c);
                    map[r][cc] = Math.min(map[r][cc], h);
                    continue;
                }
                int a = r * M + c, b = r * M + (c + 1);
                edgeList[a].add(new Edge(a, b, h));
                edgeList[b].add(new Edge(b, a, h));
            }
        }

        System.out.print(solution());
    }
}
