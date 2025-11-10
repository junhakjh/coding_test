import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int from, to;
    long cost, originCost, realCost;

    Edge(int from, int to, long cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.originCost = cost;
        this.realCost = cost;
    }

    Edge(int from, int to, long cost,long  originCost, long realCost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.originCost = originCost;
        this.realCost = realCost;
    }

    public int compareTo(Edge edge) {
        return Long.compare(this.cost, edge.cost);
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static List<Edge>[] edgeList;

    static long solution() {
        long answer = 0;

        long[] memo = new long[N + 1], realMemo = new long[N + 1];
        Arrays.fill(memo, Long.MAX_VALUE);
        Arrays.fill(realMemo, Long.MAX_VALUE);
        memo[1] = 0;
        realMemo[1] = 0;
        Queue<Edge> pq = new PriorityQueue<>();
        for (Edge e : edgeList[1]) {
            pq.offer(e);
        }
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (memo[e.to] < e.cost) {
                continue;
            }
            if (memo[e.to] == e.cost && realMemo[e.to] <= e.realCost) {
                continue;
            }
            memo[e.to] = e.cost;
            realMemo[e.to] = e.realCost;
            long minusCost = e.originCost / 10;
            for (Edge ne : edgeList[e.to]) {
                if (memo[ne.to] < e.cost + ne.cost) {
                    continue;
                }
                pq.offer(new Edge(ne.from, ne.to, e.cost + ne.cost, ne.originCost, e.realCost + ne.cost - minusCost));
            }
        }
        for (int i = 1; i <= N; i++) {
            answer += realMemo[i];
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            edgeList[a].add(new Edge(a, b, c));
            edgeList[b].add(new Edge(b, a, c));
        }

        System.out.print(solution());
    }
}
