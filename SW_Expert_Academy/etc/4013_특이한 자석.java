import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int k;
	static int[][] magnets;
	static int[][] orders;

	static void dfs(int cur, int dir, int[] indices, boolean[] visited) {
		if (cur > 0 && !visited[cur - 1]) {
			if (magnets[cur][(indices[cur] + 6) % 8] != magnets[cur - 1][(indices[cur - 1] + 2) % 8]) {
				visited[cur - 1] = true;
				dfs(cur - 1, dir * (-1), indices, visited);
			}
		}
		if (cur < 3 && !visited[cur + 1]) {
			if (magnets[cur][(indices[cur] + 2) % 8] != magnets[cur + 1][(indices[cur + 1] + 6) % 8]) {
				visited[cur + 1] = true;
				dfs(cur + 1, dir * (-1), indices, visited);
			}
		}

		indices[cur] -= dir;
		if (indices[cur] == 8) {
			indices[cur] = 0;
		} else if (indices[cur] == -1) {
			indices[cur] = 7;
		}

	}

	static int solution() {
		int answer = 0;

		int[] indices = new int[4];
		for (int[] order : orders) {
			int magnet = order[0], dir = order[1];
			boolean[] visited = new boolean[4];
			visited[magnet] = true;
			dfs(magnet, dir, indices, visited);
		}
		for (int i = 0; i < 4; i++) {
			if (magnets[i][indices[i]] == 1) {
				answer += Math.pow(2, i);
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			k = Integer.parseInt(input.readLine());
			magnets = new int[4][8];
			orders = new int[k][2];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(input.readLine());
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(input.readLine());
				orders[i] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
