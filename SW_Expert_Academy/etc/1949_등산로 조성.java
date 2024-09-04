import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 4.
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq
 * @timecomplex
 * @performance 21,068 kb, 121 ms
 * @category #dfs #백트래킹
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	static int n, k, maxH;
	static int[][] map;

	static int answer;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n) {
			return false;
		}
		return true;
	}

	static void dfs(int r, int c, int len, boolean canDown, boolean[][] visited) {
		boolean check = false;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i], nc = c + dc[i];
			if (isIn(nr, nc) && !visited[nr][nc]) {
				if (map[nr][nc] < map[r][c]) {
					check = true;
					visited[nr][nc] = true;
					dfs(nr, nc, len + 1, canDown, visited);
					visited[nr][nc] = false;
				} else if (canDown && map[nr][nc] - k < map[r][c]) {
					check = true;
					visited[nr][nc] = true;
					int temp = map[nr][nc];
					map[nr][nc] = map[r][c] - 1;
					dfs(nr, nc, len + 1, false, visited);
					map[nr][nc] = temp;
					visited[nr][nc] = false;
				}
			}
		}
		if (!check) {
			answer = Math.max(answer, len);
		}
	}

	static void solution() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] == maxH) {
					boolean[][] visited = new boolean[n][n];
					visited[r][c] = true;
					dfs(r, c, 1, true, visited);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			maxH = 0;
			map = new int[n][n];
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, map[r][c]);
				}
			}

			solution();

			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(output);
	}
}
