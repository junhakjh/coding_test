import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 2.
 * @link https://www.acmicpc.net/problem/10971
 * @timecomplex
 * @performance 14,696 kb, 132 ms
 * @category #dfs
 * @note
 */

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n;
	static int[][] map;
	static int[] order;
	static boolean[] visited;

	static int answer;

	static void dfs(int depth, int prevI, int sum) {
		if (depth == n - 1) {
			if (map[prevI][0] != 0) {
				answer = Math.min(answer, sum + map[prevI][0]);
			}
			return;
		}

		for (int i = 1; i < n; i++) {
			if (!visited[i] && map[prevI][i] != 0) {
				visited[i] = true;
				order[depth] = i;
				dfs(depth + 1, i, sum + map[prevI][i]);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		answer = Integer.MAX_VALUE;
		n = Integer.parseInt(input.readLine());
		map = new int[n][n];
		order = new int[n];
		visited = new boolean[n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);

		System.out.print(answer);
	}
}
