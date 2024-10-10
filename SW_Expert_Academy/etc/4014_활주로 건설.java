import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, X;
	static int[][] map;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}
		return true;
	}

	static int getRowNum() {
		int result = 0;

		label: for (int r = 0; r < N; r++) {
			int prev = map[r][0];
			boolean[] visited = new boolean[N];
			for (int c = 1; c < N; c++) {
				if (map[r][c] - prev == 1) {
					for (int i = 1; i <= X; i++) {
						if (!isIn(r, c - i) || visited[c - i] || map[r][c - i] != map[r][c] - 1) {
							continue label;
						}
					}
					for (int i = 1; i <= X; i++) {
						visited[c - i] = true;
					}
				} else if (map[r][c] - prev == -1) {
					for (int i = 0; i < X; i++) {
						if (!isIn(r, c + i) || visited[c + i] || map[r][c + i] != map[r][c]) {
							continue label;
						}
					}
					for (int i = 0; i < X; i++) {
						visited[c + i] = true;
					}
				} else if (map[r][c] - prev == 0) {
					continue;
				} else {
					continue label;
				}

				prev = map[r][c];
			}
			result++;
		}

		return result;
	}

	static int getColNum() {
		int result = 0;

		label: for (int c = 0; c < N; c++) {
			int prev = map[0][c];
			boolean[] visited = new boolean[N];
			for (int r = 1; r < N; r++) {
				if (map[r][c] - prev == 1) {
					for (int i = 1; i <= X; i++) {
						if (!isIn(r - i, c) || visited[r - i] || map[r - i][c] != map[r][c] - 1) {
							continue label;
						}
					}
					for (int i = 1; i <= X; i++) {
						visited[r - i] = true;
					}
				} else if (map[r][c] - prev == -1) {
					for (int i = 0; i < X; i++) {
						if (!isIn(r + i, c) || visited[r + i] || map[r + i][c] != map[r][c]) {
							continue label;
						}
					}
					for (int i = 0; i < X; i++) {
						visited[r + i] = true;
					}
				} else if (map[r][c] - prev == 0) {
					continue;
				} else {
					continue label;
				}

				prev = map[r][c];
			}
			result++;
		}

		return result;
	}

	static int solution() {
		int answer = 0;

		answer += getRowNum() + getColNum();

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
