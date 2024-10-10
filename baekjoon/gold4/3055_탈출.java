import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int R, C;
	static int[] start;
	static char[][] map;
	static List<int[]> waterPos;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C) {
			return false;
		}
		return true;
	}

	static int solution() {
		int answer = -1;

		int[][] waterMap = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				waterMap[r][c] = Integer.MAX_VALUE;
			}
		}
		for (int[] water : waterPos) {
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] { water[0], water[1], 0 });
			waterMap[water[0]][water[1]] = 0;
			while (!q.isEmpty()) {
				int[] item = q.poll();
				int r = item[0], c = item[1], time = item[2];
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i], nc = c + dc[i];
					if (isIn(nr, nc) && waterMap[nr][nc] > time + 1 && map[nr][nc] == '.') {
						waterMap[nr][nc] = time + 1;
						q.offer(new int[] { nr, nc, time + 1 });
					}
				}
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { start[0], start[1], 0 });
		boolean[][] visited = new boolean[R][C];
		visited[start[0]][start[1]] = true;
		while (!q.isEmpty()) {
			int[] item = q.poll();
			int r = item[0], c = item[1], time = item[2];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (isIn(nr, nc) && !visited[nr][nc] && waterMap[nr][nc] > time + 1) {
					if (map[nr][nc] == 'D') {
						return time + 1;
					} else if (map[nr][nc] == '.') {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc, time + 1 });
					}
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		waterPos = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			String str = input.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == '*') {
					waterPos.add(new int[] { r, c });
				} else if (map[r][c] == 'S') {
					start = new int[] { r, c };
				}
			}
		}

		int answer = solution();
		System.out.print(answer == -1 ? "KAKTUS" : answer);
	}

}
