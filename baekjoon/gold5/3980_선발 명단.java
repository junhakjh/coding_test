import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int[][] infos;

	static int dfs(int r, int curScore, boolean[] visited) {
		int maxScore = 0;

		for (int c = 0; c < 11; c++) {
			if (!visited[c] && infos[r][c] != 0) {
				if (r == 10) {
					maxScore = Math.max(maxScore, curScore + infos[r][c]);
				} else {
					visited[c] = true;
					maxScore = Math.max(maxScore, dfs(r + 1, curScore + infos[r][c], visited));
					visited[c] = false;
				}
			}
		}

		return maxScore;
	}

	public static void main(String[] args) throws Exception {
		int C = Integer.parseInt(input.readLine());
		for (int tc = 0; tc < C; tc++) {
			infos = new int[11][11];
			for (int r = 0; r < 11; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < 11; c++) {
					infos[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			output.append(dfs(0, 0, new boolean[11])).append("\n");
		}
		System.out.print(output);
	}
}
