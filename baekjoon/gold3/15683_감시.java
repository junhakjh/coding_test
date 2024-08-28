import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 28.
 * @link https://www.acmicpc.net/problem/15683
 * @timecomplex
 * @performance 37,244 kb, 288 ms
 * @category #백트래킹
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int n, m;
	static int[][] map;
	static List<int[]> cctvs = new ArrayList<>();

	static int answer = Integer.MAX_VALUE;

	static boolean check(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m || map[r][c] == 6) {
			return false;
		}
		return true;
	}

	static void dfs(int curI) {
		if (curI == cctvs.size()) {
			int sum = 0;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < m; c++) {
					if (map[r][c] == 0) {
						sum++;
					}
				}
			}
			answer = Math.min(answer, sum);
			return;
		}

		int r = cctvs.get(curI)[0], c = cctvs.get(curI)[1];
		switch (map[r][c]) {
		case 1:
			for (int i = 0; i < 4; i++) {
				List<int[]> toZero = new ArrayList<>();
				int nr = r + dr[i], nc = c + dc[i];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[i];
					nc += dc[i];
				}
				dfs(curI + 1);
				for (int[] coor : toZero) {
					map[coor[0]][coor[1]] = 0;
				}
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				List<int[]> toZero = new ArrayList<>();
				int nr = r + dr[i], nc = c + dc[i];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[i];
					nc += dc[i];
				}
				nr = r - dr[i];
				nc = c - dc[i];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr -= dr[i];
					nc -= dc[i];
				}
				dfs(curI + 1);
				for (int[] coor : toZero) {
					map[coor[0]][coor[1]] = 0;
				}
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				List<int[]> toZero = new ArrayList<>();
				int nr = r + dr[i], nc = c + dc[i];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[i];
					nc += dc[i];
				}
				nr = r + dr[(i + 1) % 4];
				nc = c + dc[(i + 1) % 4];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[(i + 1) % 4];
					nc += dc[(i + 1) % 4];
				}
				dfs(curI + 1);
				for (int[] coor : toZero) {
					map[coor[0]][coor[1]] = 0;
				}
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				List<int[]> toZero = new ArrayList<>();
				int nr = r + dr[i], nc = c + dc[i];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[i];
					nc += dc[i];
				}
				nr = r + dr[(i + 1) % 4];
				nc = c + dc[(i + 1) % 4];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[(i + 1) % 4];
					nc += dc[(i + 1) % 4];
				}
				nr = r + dr[(i + 2) % 4];
				nc = c + dc[(i + 2) % 4];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[(i + 2) % 4];
					nc += dc[(i + 2) % 4];
				}
				dfs(curI + 1);
				for (int[] coor : toZero) {
					map[coor[0]][coor[1]] = 0;
				}
			}
			break;
		case 5:
			List<int[]> toZero = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				while (check(nr, nc)) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
						toZero.add(new int[] { nr, nc });
					}
					nr += dr[i];
					nc += dc[i];
				}
			}
			dfs(curI + 1);
			for (int[] coor : toZero) {
				map[coor[0]][coor[1]] = 0;
			}
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0 && map[r][c] != 6) {
					cctvs.add(new int[] { r, c });
				}
			}
		}

		dfs(0);

		System.out.println(answer);
	}
}
