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

	Union(int n, Set<Integer> powers) {
		parents = new int[n + 1];
		for (int power : powers) {
			parents[power] = -1;
		}
	}

	int findRoot(int i) {
		if (parents[i] <= 0) {
			return parents[i];
		}
		return findRoot(parents[i]);
	}

	boolean union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);

		if (aRoot < 0 && bRoot < 0) {
			return false;
		}

		parents[a] = -1;
		parents[b] = -1;

		return true;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M, K;
	static Set<Integer> powers;
	static Map<Integer, List<Edge>> edges;

	static int solution() {
		int answer = 0;

		Union union = new Union(N, powers);
		Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.w, e2.w));
		for (int power : powers) {
			for (Edge edge : edges.get(power)) {
				pq.offer(edge);
			}
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
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		powers = new HashSet<>();
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < K; i++) {
			powers.add(Integer.parseInt(st.nextToken()));
		}
		edges = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			edges.put(i, new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.get(v1).add(new Edge(v1, v2, w));
			edges.get(v2).add(new Edge(v2, v1, w));
		}

		System.out.println(solution());
	}
}
