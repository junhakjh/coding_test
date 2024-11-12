import java.util.*;
import java.io.*;

class Edge {
	int v;
	int w;

	Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int V;
	static Map<Integer, List<Edge>> map;

	static int solution() {
		int answer = 0;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 1, 0 });
		int maxW = 0, root = 1;
		boolean[] visited = new boolean[V + 1];
		visited[1] = true;
		while (!q.isEmpty()) {
			int[] item = q.poll();
			int v = item[0], w = item[1];
			for (Edge edge : map.get(v)) {
				if (!visited[edge.v]) {
					visited[edge.v] = true;
					if (w + edge.w > maxW) {
						maxW = w + edge.w;
						root = edge.v;
					}
					q.offer(new int[] { edge.v, w + edge.w });
				}
			}
		}
		q = new ArrayDeque<>();
		q.offer(new int[] { root, 0 });
		visited = new boolean[V + 1];
		visited[root] = true;
		while (!q.isEmpty()) {
			int[] item = q.poll();
			int v = item[0], w = item[1];
			for (Edge edge : map.get(v)) {
				if (!visited[edge.v]) {
					visited[edge.v] = true;
					answer = Math.max(w + edge.w, answer);
					q.offer(new int[] { edge.v, w + edge.w });
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		V = Integer.parseInt(input.readLine());
		map = new HashMap<>();
		for (int v = 1; v <= V; v++) {
			map.put(v, new ArrayList<>());
		}
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(input.readLine());
			int e1 = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				int e2 = Integer.parseInt(st.nextToken());
				if (e2 == -1) {
					break;
				}
				int w = Integer.parseInt(st.nextToken());
				map.get(e1).add(new Edge(e2, w));
			}
		}

		System.out.println(solution());
	}
}
