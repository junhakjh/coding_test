import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] costs, indegrees;
	static Set<Integer>[] needs;
	static Queue<Integer> q;

	static void solution() {
		int[] times = new int[N + 1];

		while (!q.isEmpty()) {
			int idx = q.poll();
			times[idx] += costs[idx];
			for (int next : needs[idx]) {
				times[next] = Math.max(times[next], times[idx]);
				if (--indegrees[next] == 0) {
					q.offer(next);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			output.append(times[i]).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		costs = new int[N + 1];
		indegrees = new int[N + 1];
		needs = new HashSet[N + 1];
		q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			needs[i] = new HashSet<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(input.readLine());
			costs[i] = Integer.parseInt(st.nextToken());
			int num;
			while ((num = Integer.parseInt(st.nextToken())) != -1) {
				needs[num].add(i);
				indegrees[i]++;
			}
			if (indegrees[i] == 0) {
				q.offer(i);
			}
		}

		solution();

		System.out.println(output);
	}
}
