import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;
	static int islands = 0;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static int solution() {
		int answer = 0;
		int year = 0;

		label: while (islands > 0) {
			boolean[][] visited = new boolean[N][M];
			int island = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] > 0 && !visited[r][c]) {
						if (++island > 1) {
							answer = year;
							break label;
						}
						Queue<int[]> q = new ArrayDeque<>();
						q.offer(new int[] { r, c });
						visited[r][c] = true;
						while (!q.isEmpty()) {
							int[] item = q.poll();
							for (int i = 0; i < 4; i++) {
								int nr = item[0] + dr[i], nc = item[1] + dc[i];
								if (isIn(nr, nc) && map[nr][nc] > 0 && !visited[nr][nc]) {
									visited[nr][nc] = true;
									q.offer(new int[] { nr, nc });
								}
							}
						}
					}
				}
			}

			year++;

			List<int[]> cand = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] > 0) {
						int num = 0;
						for (int i = 0; i < 4; i++) {
							int nr = r + dr[i], nc = c + dc[i];
							if (isIn(nr, nc) && map[nr][nc] == 0) {
								num++;
							}
						}
						cand.add(new int[] { r, c, num });
					}
				}
			}

			for (int[] item : cand) {
				map[item[0]][item[1]] = Math.max(0, map[item[0]][item[1]] - item[2]);
				if (map[item[0]][item[1]] == 0) {
					islands--;
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] > 0) {
					islands++;
				}
			}
		}

		System.out.println(solution());
	}
}
