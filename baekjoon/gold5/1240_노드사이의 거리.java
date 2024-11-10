import java.util.*;
import java.io.*;

class Edge {
	int e;
	int w;

	Edge(int e, int w) {
		this.e = e;
		this.w = w;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static Map<Integer, List<Edge>> map;
	static int[][] questions;

	static void solution() {
		for (int[] question : questions) {
			boolean[] visited = new boolean[N + 1];
			int start = question[0], target = question[1];
			visited[start] = true;
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] { start, 0 });
			label: while (!q.isEmpty()) {
				int[] item = q.poll();
				int node = item[0], w = item[1];
				for (Edge edge : map.get(node)) {
					if (!visited[edge.e]) {
						if (edge.e == target) {
							output.append(w + edge.w).append("\n");
							break label;
						}
						visited[edge.e] = true;
						q.offer(new int[] { edge.e, w + edge.w });
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		questions = new int[M][2];
		for (int i = 1; i <= N; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(input.readLine());
			int e1 = Integer.parseInt(st.nextToken());
			int e2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map.get(e1).add(new Edge(e2, w));
			map.get(e2).add(new Edge(e1, w));
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			questions[i] = new int[] { s, e };
		}

		solution();

		System.out.println(output);
	}
}
