import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int to, c, d;

    Edge(int to, int c) {
        this.to = to;
        this.c = c;
        this.d = 1;
    }

    Edge(int to, int c, int d) {
        this.to = to;
        this.c = c;
        this.d = d;
    }

    public int compareTo(Edge e) {
        if (this.d != e.d) {
            return Integer.compare(this.d, e.d);
        }
        return Integer.compare(this.c, e.c);
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M, K, S, D;
    static List<Edge>[] edges;
    static int[] increases;

    static void solution() {
        int[][] costs = new int[N + 1][N];
        int[] minCosts = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < N; j++) {
                costs[i][j] = Integer.MAX_VALUE;
            }
        }
        costs[S][0] = 0;
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        minCosts[S] = 0;
        Queue<Edge> pq = new PriorityQueue<>();
        for (Edge e : edges[S]) {
            pq.offer(e);
        }
        while (!pq.isEmpty()) {
            Edge ce = pq.poll();
            if (minCosts[ce.to] <= ce.c) {
                continue;
            }
            costs[ce.to][ce.d] = ce.c;
            minCosts[ce.to] = ce.c;
            if (ce.to == D || ce.d == N - 1) {
                continue;
            }
            for (Edge ne : edges[ce.to]) {
                if (minCosts[ne.to] <= ce.c + ne.c) {
                    continue;
                }
                pq.offer(new Edge(ne.to, ce.c + ne.c, ce.d + 1));
            }
        }

        for (int increase : increases) {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < N; i++) {
                if (costs[D][i] == Integer.MAX_VALUE) {
                    continue;
                }
                costs[D][i] += i * increase;
                min = Math.min(min, costs[D][i]);
            }
            output.append(min).append('\n');
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(input.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }
        increases = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            increases[i] = Integer.parseInt(input.readLine());
        }

        solution();

        System.out.print(output);
    }
}
