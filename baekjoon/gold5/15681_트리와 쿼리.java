import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, R, Q;
	static Map<Integer, List<Integer>> map;
	static int[] query;
	static int[] parents;
	static boolean[] visited;

	static int dfs(int node) {
		for (int nn : map.get(node)) {
			if (!visited[nn]) {
				visited[nn] = true;
				parents[node] += dfs(nn);
			}
		}

		return parents[node];
	}

	static void solution() {
		visited[R] = true;

		dfs(R);

		for (int node : query) {
			output.append(parents[node]).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		query = new int[Q];
		parents = new int[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			map.put(i, new ArrayList<>());
			parents[i] = 1;
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(input.readLine());
			int e1 = Integer.parseInt(st.nextToken());
			int e2 = Integer.parseInt(st.nextToken());
			map.get(e1).add(e2);
			map.get(e2).add(e1);
		}
		for (int i = 0; i < Q; i++) {
			query[i] = Integer.parseInt(input.readLine());
		}

		solution();

		System.out.println(output);
	}
}
