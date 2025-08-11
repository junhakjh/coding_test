import java.util.*;
import java.io.*;

class Edge {
    int target;
    int cost;

    Edge(int target, int cost) {
        this.target = target;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, Q;
    static Map<Integer, List<Edge>> map;
    static int[][] questions;

    static void solution() {
        int[][] minCostMap = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{i, Integer.MAX_VALUE});
            while (!q.isEmpty()) {
                int[] item = q.poll();
                int node = item[0], curCost = item[1];
                minCostMap[i][node] = curCost;
                for (Edge edge : map.get(node)) {
                    if (visited[edge.target]) {
                        continue;
                    }
                    visited[edge.target] = true;
                    q.offer(new int[]{edge.target, Math.min(curCost, edge.cost)});
                }
            }
        }

        for (int[] question : questions) {
            int k = question[0], startIdx = question[1];
            int num = 0;
            for (int i = 1; i <= N; i++) {
                if (startIdx != i && minCostMap[startIdx][i] >= k) {
                    num++;
                }
            }
            output.append(num).append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
            map.putIfAbsent(a, new ArrayList<>());
            map.putIfAbsent(b, new ArrayList<>());
            map.get(a).add(new Edge(b, cost));
            map.get(b).add(new Edge(a, cost));
        }
        questions = new int[Q][2];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(input.readLine());
            questions[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        solution();

        System.out.println(output);
    }
}
