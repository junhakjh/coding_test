import java.util.*;
import java.io.*;

class Union {
    int[] parents;

    Union(int N) {
        this.parents = new int[N];
        for (int i = 0; i < N; i++) {
            this.parents[i] = -1;
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
    double[] a, b;
    int aIdx, bIdx;
    double value;

    Edge(double[] a, int aIdx, double[] b, int bIdx) {
        this.a = a;
        this.aIdx = aIdx;
        this.b = b;
        this.bIdx = bIdx;
        this.value = Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static double[][] coordinates;

    static double solution() {
        double answer = 0;

        Queue<Edge> pq = new PriorityQueue<>((a, b) -> a.value > b.value ? 1 : -1);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                pq.offer(new Edge(coordinates[i], i, coordinates[j], j));
            }
        }

        Union union = new Union(N);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union.union(edge.aIdx, edge.bIdx)) {
                answer += edge.value;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        coordinates = new double[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            coordinates[i][0] = Double.parseDouble(st.nextToken());
            coordinates[i][1] = Double.parseDouble(st.nextToken());
        }

        System.out.println(solution());
    }
}
