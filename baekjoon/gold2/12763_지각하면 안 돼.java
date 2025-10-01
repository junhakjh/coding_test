import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
	int node, cost, time;

	Edge(int node, int time, int cost) {
		this.node = node;
		this.time = time;
		this.cost = cost;
	}

	public int compareTo(Edge e) {
		if (cost == e.cost) {
			return Integer.compare(time, e.time);
		}
		return Integer.compare(cost, e.cost);
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, T, M, L;
	static List<Edge> edges[];

	static int solution() {
		Queue<Edge> pq = new PriorityQueue<>();
		for (Edge e : edges[1]) {
			if (e.time <= T && e.cost <= M) {
				pq.offer(e);
			}
		}
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (e.node == N) {
				return e.cost;
			}
			for (Edge ne : edges[e.node]) {
				if (e.time + ne.time <= T && e.cost + ne.cost <= M) {
					pq.offer(new Edge(ne.node, e.time + ne.time, e.cost + ne.cost));
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		st = new StringTokenizer(input.readLine());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		L = Integer.parseInt(input.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
					time = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, time, cost));
			edges[b].add(new Edge(a, time, cost));
		}

		System.out.println(solution());
	}
}
