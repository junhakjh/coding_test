import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 2.
 * @link https://www.acmicpc.net/problem/1753
 * @timecomplex
 * @performance 118,300 kb, 732 ms
 * @category #다익스트라 #힙
 * @note
 */

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

	static int v, e, k;
	static Map<Integer, List<Edge>> edges;
	static int[] weights;
	static boolean[] visited;

	static void solution() {
		Queue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		q.offer(new int[] { k, 0 });
		while (!q.isEmpty()) {
			int i = q.poll()[0];
			for (Edge edge : edges.get(i)) {
				if (weights[edge.v] > weights[i] + edge.w) {
					weights[edge.v] = weights[i] + edge.w;
					q.offer(new int[] { edge.v, weights[edge.v] });
				}
			}
		}

		for (int i = 1; i <= v; i++) {
			output.append(weights[i] == Integer.MAX_VALUE ? "INF" : weights[i]).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(input.readLine());
		edges = new HashMap<>();
		weights = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			weights[i] = Integer.MAX_VALUE;
		}
		weights[k] = 0;
		for (int i = 1; i <= v; i++) {
			edges.put(i, new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(input.readLine());
			edges.get(Integer.parseInt(st.nextToken()))
					.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		solution();

		System.out.print(output);
	}
}
