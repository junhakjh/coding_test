import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int BEER = 20;

	static int N;
	static int[] start, target;
	static int[][] marts;

	static boolean canGoTarget(int[] start, int[] target) {
		if (Math.abs(start[0] - target[0]) + Math.abs(start[1] - target[1]) <= 50 * BEER) {
			return true;
		}
		return false;
	}

	static boolean solution() {
		boolean[] visited = new boolean[N];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		while (!q.isEmpty()) {
			int[] prev = q.poll();
			if (canGoTarget(prev, target)) {
				return true;
			}

			for (int i = 0; i < N; i++) {
				int[] mart = marts[i];
				if (!visited[i] && canGoTarget(prev, mart)) {
					visited[i] = true;
					q.offer(mart);
				}
			}
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(input.readLine());
			st = new StringTokenizer(input.readLine());
			start = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			marts = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(input.readLine());
				marts[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			}
			st = new StringTokenizer(input.readLine());
			target = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };

			output.append(solution() ? "happy" : "sad").append("\n");
		}

		System.out.print(output);
	}

}
