import java.util.*;
import java.io.*;

class Edge {
	int target, cost;

	Edge(int target, int cost) {
		this.target = target;
		this.cost = cost;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M, X;
	static Map<Integer, List<Edge>> map;

	static int solution() {
		int answer = 0;

		int[] toX = new int[N + 1], fromX = new int[N + 1];
		for (int start = 1; start <= N; start++) {
			if (start == X) {
				continue;
			}
			Queue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
			for (Edge edge : map.get(start)) {
				pq.offer(edge);
			}
			boolean[] visited = new boolean[N + 1];
			visited[start] = true;
			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				if (edge.target == X) {
					toX[start] = edge.cost;
					break;
				}
				if (visited[edge.target]) {
					continue;
				}
				visited[edge.target] = true;
				for (Edge nextEdge : map.get(edge.target)) {
					if (visited[nextEdge.target]) {
						continue;
					}
					pq.offer(new Edge(nextEdge.target, edge.cost + nextEdge.cost));
				}
			}
		}

		Queue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
		for (Edge edge : map.get(X)) {
			pq.offer(edge);
		}
		boolean[] visited = new boolean[N + 1];
		visited[X] = true;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (visited[edge.target]) {
				continue;
			}
			visited[edge.target] = true;
			fromX[edge.target] = edge.cost;
			for (Edge nextEdge : map.get(edge.target)) {
				if (visited[nextEdge.target]) {
					continue;
				}
				pq.offer(new Edge(nextEdge.target, edge.cost + nextEdge.cost));
			}
		}

		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, toX[i] + fromX[i]);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			map.put(i, new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			map.get(Integer.parseInt(st.nextToken()))
					.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		System.out.println(solution());
	}
}
