import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 14.
 * @link https://www.acmicpc.net/problem/18428
 * @timecomplex O(?)
 * @performance 14,348 kb, 108 ms
 * @category #dfs #백트래킹
 * @note
 */

class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int n;
	static char[][] map;
	static int[] indice = new int[3];
	static List<int[]> teachers = new ArrayList<>();
	static boolean answer = false;

	static void check() {
		for (int[] teacher : teachers) {
			int r = teacher[0], c = teacher[1];
			for (int i = 0; i < 4; i++) {
				int nr = r, nc = c;
				while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
					if (map[nr][nc] == 'S') {
						return;
					}
					if (map[nr][nc] == 'O') {
						break;
					}
					nr += dr[i];
					nc += dc[i];
				}
			}
		}
		answer = true;
	}

	static void comb(int curI, int depth) {
		if (answer) {
			return;
		}
		if (depth == 3) {
			check();
			return;
		}

		for (int i = curI; i < n * n; i++) {
			int r = i / n, c = i % n;
			if (map[r][c] == 'X') {
				map[r][c] = 'O';
				comb(i + 1, depth + 1);
				if (answer) {
					return;
				}
				map[r][c] = 'X';
			}
		}
	}

	public static void main(String args[]) throws Exception {
		n = Integer.parseInt(input.readLine());
		map = new char[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = st.nextToken().charAt(0);
				if (map[r][c] == 'T') {
					teachers.add(new int[] { r, c });
				}
			}
		}

		comb(0, 0);

		System.out.print(answer ? "YES" : "NO");
	}
}
