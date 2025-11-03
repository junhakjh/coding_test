import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
	int a, b, c;

	Edge(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int compareTo(Edge e) {
		return Integer.compare(this.c, e.c);
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static List<Edge>[] edges;

	static boolean isSameRoad(int a, int b, int c, int d) {
		return (a == c && b == d) || (a == d && b == c);
	}

	static int solution() {
		int answer = 0;

		int[] track = new int[N + 1], memo = new int[N + 1];
		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[1] = 0;
		Queue<Edge> pq = new PriorityQueue<>();
		for (Edge edge : edges[1]) {
			pq.offer(edge);
		}
		while (!pq.isEmpty()) {
			Edge curEdge = pq.poll();
			if (memo[curEdge.b] <= curEdge.c) {
				continue;
			}
			track[curEdge.b] = curEdge.a;
			memo[curEdge.b] = curEdge.c;
			for (Edge nextEdge : edges[curEdge.b]) {
				pq.offer(new Edge(nextEdge.a, nextEdge.b, curEdge.c + nextEdge.c));
			}
		}

		int min = memo[N];

		int idx = N;
		while (idx != 1) {
			int next = track[idx];
			memo = new int[N + 1];
			Arrays.fill(memo, Integer.MAX_VALUE);
			memo[1] = 0;
			pq = new PriorityQueue<>();
			for (Edge edge : edges[1]) {
				if (isSameRoad(edge.a, edge.b, idx, next)) {
					continue;
				}
				pq.offer(edge);
			}
			while (!pq.isEmpty()) {
				Edge curEdge = pq.poll();
				if (memo[curEdge.b] <= curEdge.c) {
					continue;
				}
				memo[curEdge.b] = curEdge.c;
				for (Edge nextEdge : edges[curEdge.b]) {
					if (isSameRoad(nextEdge.a, nextEdge.b, idx, next)) {
						continue;
					}
					pq.offer(new Edge(nextEdge.a, nextEdge.b, curEdge.c + nextEdge.c));
				}
			}

			answer = Math.max(answer, memo[N]);
			if (answer == Integer.MAX_VALUE) {
				break;
			}

			idx = next;
		}

		return answer == Integer.MAX_VALUE ? -1 : answer - min;
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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(a, b, c));
			edges[b].add(new Edge(b, a, c));
		}

		System.out.println(solution());
	}
}
