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

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int s, e;
	static Map<Integer, List<Edge>> edges;

	static int solution() {
		int[] nodes = new int[N];
		nodes[s] = Integer.MAX_VALUE;
		Queue<Edge> pq = new PriorityQueue<>(
				(e1, e2) -> (-1) * Integer.compare(Math.min(nodes[e1.v1], e1.w), Math.min(nodes[e2.v1], e2.w)));
		for (Edge edge : edges.get(s)) {
			pq.offer(edge);
		}
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (Math.min(nodes[edge.v1], edge.w) > nodes[edge.v2]) {
				nodes[edge.v2] = Math.min(nodes[edge.v1], edge.w);
				if (edge.v2 == e) {
					continue;
				}
				for (Edge nEdge : edges.get(edge.v2)) {
					pq.offer(nEdge);
				}
			}
		}

		return nodes[e];
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(input.readLine());
		s = Integer.parseInt(st.nextToken()) - 1;
		e = Integer.parseInt(st.nextToken()) - 1;
		edges = new HashMap<>();
		for (int i = 0; i < N; i++) {
			edges.put(i, new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			edges.get(v1).add(new Edge(v1, v2, w));
			edges.get(v2).add(new Edge(v2, v1, w));
		}

		System.out.println(solution());
	}
}
