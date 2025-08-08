import java.util.*;
import java.io.*;

class Union {
    int[] parents;

    Union(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
    }

    int findRoot(int idx) {
        if (parents[idx] < 0) {
            return idx;
        }
        return parents[idx] = findRoot(parents[idx]);
    }

    boolean union(int a, int b) {
        int aRoot = findRoot(a), bRoot = findRoot(b);
        if (aRoot == bRoot) {
            return false;
        }
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}

class Edge {
    int e1, e2, cost;

    Edge(int e1, int e2, int cost) {
        this.e1 = e1;
        this.e2 = e2;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int M, N, costSum;
    static PriorityQueue<Edge> pq;

    static int solution() {
        int answer = costSum;

        Union union = new Union(M);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union.union(edge.e1, edge.e2)) {
                answer -= edge.cost;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            st = new StringTokenizer(input.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            costSum = 0;
            if (M == 0 && N == 0) {
                break;
            }
            pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                int e1 = Integer.parseInt(st.nextToken()), e2 = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
                pq.offer(new Edge(e1, e2, cost));
                costSum += cost;
            }
            output.append(solution()).append("\n");
        }

        System.out.print(output);
    }
}
