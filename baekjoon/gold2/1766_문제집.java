import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] indegree;
	static Map<Integer, List<Integer>> graph;

	static void solution() {
		Queue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				pq.offer(i);
			}
		}

		while (!pq.isEmpty()) {
			int a = pq.poll();
			output.append(a).append(" ");
			for (int b : graph.get(a)) {
				if (--indegree[b] == 0) {
					pq.offer(b);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N + 1];
		graph = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			graph.put(i, new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			indegree[b]++;
			graph.get(a).add(b);
		}

		solution();

		System.out.print(output);
	}
}
