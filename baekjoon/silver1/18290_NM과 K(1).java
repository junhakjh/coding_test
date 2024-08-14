import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 14.
 * @link https://www.acmicpc.net/problem/18290
 * @timecomplex O(?)
 * @performance 17,352 ms, 2,100 ms
 * @category #브루트포스
 * @note
 */

class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };
	static int n, m, k;
	static int[][] map;
	static int answer = Integer.MIN_VALUE;
	static int visited[][];

	static void find(int sum, int depth) {
		if (depth == k) {
			answer = Math.max(sum, answer);
			return;
		}
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (visited[r][c] == 0) {
					visited[r][c]++;
					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i], nc = c + dc[i];
						if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
							visited[nr][nc]++;
						}
					}
					find(sum + map[r][c], depth + 1);
					visited[r][c]--;
					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i], nc = c + dc[i];
						if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
							visited[nr][nc]--;
						}
					}
				}
			}
		}
	}

	public static void main(String args[]) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];

		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		find(0, 0);

		System.out.print(answer);
	}
}
