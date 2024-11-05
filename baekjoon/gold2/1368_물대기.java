import java.util.*;
import java.io.*;

class Edge {
	int v1;
	int v2;
	int w;

	Edge(int v1, int v2, int w) {
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}
}

class Union {
	int[] parents;

	Union(int n) {
		parents = new int[n + 1];
		for (int i = 0; i <= n; i++) {
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
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);

		if (aRoot == bRoot) {
			return false;
		}

		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;

		return true;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] well;
	static Map<Integer, List<Edge>> edges;

	static int solution() {
		int answer = 0;

		Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.w, e2.w));
		Union union = new Union(N);
		for (Edge edge : edges.get(0)) {
			pq.offer(edge);
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union.union(edge.v1, edge.v2)) {
				answer += edge.w;
				for (Edge ne : edges.get(edge.v2)) {
					pq.offer(ne);
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		well = new int[N];
		edges = new HashMap<>();
		for (int i = 0; i <= N; i++) {
			edges.put(i, new ArrayList<>());
		}
		for (int i = 0; i < N; i++) {
			int w = Integer.parseInt(input.readLine());
			edges.get(i).add(new Edge(i, N, w));
			edges.get(N).add(new Edge(N, i, w));
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < N; j++) {
				int w = Integer.parseInt(st.nextToken());
				if (w > 0) {
					edges.get(i).add(new Edge(i, j, w));
				}
			}
		}

		System.out.println(solution());
	}
}
