import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 3.
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq
 * @timecomplex
 * @performance 26,048 kb, 140 ms
 * @category #bfs
 * @note
 */

class Cell {
	int r;
	int c;
	int turnel;
	int time;

	Cell(int r, int c, int turnel, int time) {
		this.r = r;
		this.c = c;
		this.turnel = turnel;
		this.time = time;
	}
}

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[][] dr = { {}, { 1, 0, -1, 0 }, { -1, 1 }, { 0, 0 }, { -1, 0 }, { 0, 1 }, { 0, 1 }, { -1, 0 } },
			dc = { {}, { 0, 1, 0, -1 }, { 0, 0 }, { -1, 1 }, { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	static int n, m, startR, startC, l;
	static int[][] map;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m) {
			return false;
		}
		return true;
	}

	static int solution() {
		int answer = 1;

		Queue<Cell> q = new ArrayDeque<>();
		q.offer(new Cell(startR, startC, map[startR][startC], 1));
		map[startR][startC] = 0;
		while (!q.isEmpty()) {
			Cell cur = q.poll();
			if (cur.time >= l) {
				continue;
			}
			for (int i = 0; i < dr[cur.turnel].length; i++) {
				int nr = cur.r + dr[cur.turnel][i], nc = cur.c + dc[cur.turnel][i];
				if (isIn(nr, nc) && map[nr][nc] != 0) {
					int turnel = map[nr][nc];
					if (nr - cur.r == 1) {
						if (turnel == 1 || turnel == 2 || turnel == 4 || turnel == 7) {
							q.offer(new Cell(nr, nc, turnel, cur.time + 1));
							map[nr][nc] = 0;
							answer++;
						}
					} else if (nr - cur.r == -1) {
						if (turnel == 1 || turnel == 2 || turnel == 5 || turnel == 6) {
							q.offer(new Cell(nr, nc, turnel, cur.time + 1));
							map[nr][nc] = 0;
							answer++;
						}
					} else if (nc - cur.c == 1) {
						if (turnel == 1 || turnel == 3 || turnel == 6 || turnel == 7) {
							q.offer(new Cell(nr, nc, turnel, cur.time + 1));
							map[nr][nc] = 0;
							answer++;
						}
					} else {
						if (turnel == 1 || turnel == 3 || turnel == 4 || turnel == 5) {
							q.offer(new Cell(nr, nc, turnel, cur.time + 1));
							map[nr][nc] = 0;
							answer++;
						}
					}
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			startR = Integer.parseInt(st.nextToken());
			startC = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			map = new int[n][m];
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < m; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
