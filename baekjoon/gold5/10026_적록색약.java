import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 30.
 * @link https://www.acmicpc.net/problem/10026
 * @timecomplex
 * @performance 14,492 kb, 116 ms
 * @category #bfs
 * @note
 */

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	static int n;
	static int[][] map;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n) {
			return false;
		}
		return true;
	}

	static int normal() {
		int answer = 0;
		boolean[][] visited = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c]) {
					visited[r][c] = true;
					Queue<Integer> q = new ArrayDeque<>();
					q.offer(r * n + c);
					while (!q.isEmpty()) {
						int coor = q.poll();
						int curR = coor / n, curC = coor % n;
						for (int i = 0; i < 4; i++) {
							int nr = curR + dr[i], nc = curC + dc[i];
							if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[r][c]) {
								visited[nr][nc] = true;
								q.offer(nr * n + nc);
							}
						}
					}
					answer++;
				}
			}
		}

		return answer;
	}

	static int abnormal() {
		int answer = 0;
		boolean[][] visited = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c]) {
					visited[r][c] = true;
					Queue<Integer> q = new ArrayDeque<>();
					q.offer(r * n + c);
					while (!q.isEmpty()) {
						int coor = q.poll();
						int curR = coor / n, curC = coor % n;
						for (int i = 0; i < 4; i++) {
							int nr = curR + dr[i], nc = curC + dc[i];
							if (isIn(nr, nc) && !visited[nr][nc] && Math.abs(map[nr][nc]) == Math.abs(map[r][c])) {
								visited[nr][nc] = true;
								q.offer(nr * n + nc);
							}
						}
					}
					answer++;
				}
			}
		}

		return answer;
	}

	static void solution() {
		output.append(normal()).append(" ").append(abnormal());
	}

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(input.readLine());
		map = new int[n][n];
		for (int r = 0; r < n; r++) {
			String s = input.readLine();
			for (int c = 0; c < n; c++) {
				switch (s.charAt(c)) {
				case 'R':
					map[r][c] = 1;
					break;
				case 'G':
					map[r][c] = -1;
					break;
				}
			}
		}

		solution();

		System.out.println(output);

	}

}
