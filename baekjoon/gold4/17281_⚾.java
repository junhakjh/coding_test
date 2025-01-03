import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, answer = 0;
	static int[][] scores;

	static int getScore(int[] order) {
		int scoreSum = 0;
		int tajaNum = 0;
		for (int ining = 0; ining < N; ining++) {
			boolean[] roo = new boolean[3];
			int out = 0;
			while (out < 3) {
				int taja = order[tajaNum++];
				if (tajaNum == 9) {
					tajaNum = 0;
				}
				int score = scores[ining][taja];
				if (score == 0) {
					out++;
				} else {
					for (int i = 2; i >= 0; i--) {
						if (roo[i]) {
							int nextRoo = i + score;
							if (nextRoo > 2) {
								scoreSum++;
							} else {
								roo[nextRoo] = true;
							}
							roo[i] = false;
						}
					}
					if (score == 4) {
						scoreSum++;
					} else {
						roo[score - 1] = true;
					}
				}
			}
		}

		return scoreSum;
	}

	static void dfs(int depth, int[] order, boolean[] visited) {
		if (depth == 9) {
			answer = Math.max(answer, getScore(order));
			return;
		}
		if (depth == 3) {
			dfs(depth + 1, order, visited);
			return;
		}

		for (int i = 1; i < 9; i++) {
			if (!visited[i]) {
				order[depth] = i;
				visited[i] = true;
				dfs(depth + 1, order, visited);
				visited[i] = false;
			}
		}
	}

	static void solution() {
		boolean[] visited = new boolean[9];
		visited[0] = true;
		dfs(0, new int[9], visited);
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		scores = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < 9; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solution();

		System.out.println(answer);
	}
}
