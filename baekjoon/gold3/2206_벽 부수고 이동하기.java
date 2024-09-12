import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	static int n, m;
	static boolean[][] map;
	static boolean[][][] visited;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m) {
			return false;
		}
		return true;
	}

	static int solution() {
		int answer = -1;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 1, 0 });
		visited[0][0][0] = true;
		while (!q.isEmpty()) {
			int[] item = q.poll();
			int r = item[0], c = item[1], moves = item[2], crush = item[3];
			if (r == n - 1 && c == m - 1) {
				return moves;
			}
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (!isIn(nr, nc)) {
					continue;
				}
				if (!map[nr][nc]) {
					if (!visited[crush][nr][nc]) {
						visited[crush][nr][nc] = true;
						q.offer(new int[] { nr, nc, moves + 1, crush });
					}
				} else {
					if (crush == 0 && !visited[1][nr][nc]) {
						visited[1][nr][nc] = true;
						q.offer(new int[] { nr, nc, moves + 1, 1 });
					}
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new boolean[n][m];
		visited = new boolean[2][n][m];
		for (int r = 0; r < n; r++) {
			String str = input.readLine();
			for (int c = 0; c < m; c++) {
				map[r][c] = str.charAt(c) == '0' ? false : true;
			}
		}

		System.out.println(solution());
	}
}
