import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int from, to;
    long cost;

    Edge(int from, int to, long cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge edge) {
        if (this.cost != edge.cost) {
            return Long.compare(this.cost, edge.cost);
        }
        return Integer.compare(this.to, edge.to);
    }

    public String toString() {
        return String.format("from: %d, to: %d, cost: %d", this.from, this.to, this.cost);
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M, S, E;
    static List<Edge>[] edges;

    static void sToE(long[] memo, int[] track, int S, int E) {
        Queue<Edge> pq = new PriorityQueue<>();
        for (Edge e : edges[S]) {
            pq.offer(e);
        }
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (memo[e.to] < e.cost) {
                continue;
            }
            if (memo[e.to] == e.cost) {
                track[e.to] = e.from;
                continue;
            }
            memo[e.to] = e.cost;
            track[e.to] = e.from;
            if (e.to == E) {
                return;
            }
            for (Edge ne : edges[e.to]) {
                long nCost = e.cost + ne.cost;
                if (memo[ne.to] < nCost) {
                    continue;
                }
                pq.offer(new Edge(ne.from, ne.to, nCost));
            }
        }
    }

    static void eToS(long[] memo, boolean[] visited, int S, int E) {
        Queue<Edge> pq = new PriorityQueue<>();
        for (Edge e : edges[E]) {
            if (visited[e.to]) {
                continue;
            }
            pq.offer(e);
        }
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (memo[e.to] <= e.cost) {
                continue;
            }
            memo[e.to] = e.cost;
            if (e.to == S) {
                return;
            }
            for (Edge ne : edges[e.to]) {
                long nCost = e.cost + ne.cost;
                if (memo[ne.to] <= nCost || visited[ne.to]) {
                    continue;
                }
                pq.offer(new Edge(ne.from, ne.to, nCost));
            }
        }
    }

    static void setVisited(int[] track, boolean[] visited, int S, int E) {
        int i = E;
        while (i != S) {
            i = track[i];
            visited[i] = true;
        }
    }

    static long solution() {
        long answer = 0;

        long[] memo = new long[N + 1];
        int[] track = new int[N + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[S] = 0;
        sToE(memo, track, S, E);
        answer += memo[E];

        boolean[] visited = new boolean[N + 1];
        visited[E] = true;
        setVisited(track, visited, S, E);

        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[E] = 0;
        visited[S] = false;
        eToS(memo, visited, S, E);
        answer += memo[S];

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(a, b, c));
            edges[b].add(new Edge(b, a, c));
        }
        st = new StringTokenizer(input.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        System.out.print(solution());
    }
}
