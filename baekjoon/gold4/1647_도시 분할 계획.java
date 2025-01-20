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

    int findRoot(int i) {
        if (parents[i] < 0) {
            return i;
        }
        return parents[i] = findRoot(parents[i]);
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
    int node1;
    int node2;
    int value;

    Edge(int node1, int node2, int value) {
        this.node1 = node1;
        this.node2 = node2;
        this.value = value;
    }
}

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static Queue<Edge> pq;

    static int solution() {
        int answer = 0, last = 0;
        Union union = new Union(N);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union.union(edge.node1, edge.node2)) {
                answer += edge.value;
                last = edge.value;
            }
        }
        answer -= last;

        return answer;
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(node1, node2, value));
        }

        System.out.println(solution());
    }
}
