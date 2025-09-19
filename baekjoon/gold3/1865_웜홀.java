import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int MAX = 100_000_000;

	static int TC;
	static int N, M, W;
	static int[][] edges;

	static void solution() {
		int[] costs = new int[N + 1];
		Arrays.fill(costs, MAX);
		costs[1] = 0;
		for (int i = 0; i < N; i++) {
			for (int n = 1; n <= N; n++) {
				for (int target = 1; target <= N; target++) {
					if (costs[n] + edges[n][target] < costs[target]) {
						if (i == N - 1) {
							output.append("YES").append("\n");
							return;
						}
						costs[target] = costs[n] + edges[n][target];
					}
				}
			}
		}
		output.append("NO").append("\n");
	}

	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(input.readLine());
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			edges = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				Arrays.fill(edges[i], MAX);
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(input.readLine());
				int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken()),
						T = Integer.parseInt(st.nextToken());
				edges[S][E] = Math.min(edges[S][E],T);
				edges[E][S] = Math.min(edges[E][S], T);
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(input.readLine());
				int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken()),
						T = Integer.parseInt(st.nextToken());
				edges[S][E] = (-1) * T;
			}

			solution();
		}

		System.out.print(output);
	}
}
