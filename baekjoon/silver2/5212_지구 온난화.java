import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int R, C;
	static char[][] map;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C) {
			return false;
		}
		return true;
	}

	static void solution() {
		List<Integer> tobeDeleted = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'X') {

					int n = 0;
					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i], nc = c + dc[i];
						if (isIn(nr, nc)) {
							if (map[nr][nc] == '.') {
								n++;
							}
						} else {
							n++;
						}
					}
					if (n >= 3) {
						tobeDeleted.add(r * C + c);
					}
				}
			}
		}

		for (int id : tobeDeleted) {
			int r = id / C, c = id % C;
			map[r][c] = '.';
		}

		int left = C - 1, right = 0, up = R - 1, down = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'X') {
					left = Math.min(left, c);
					right = Math.max(right, c);
					up = Math.min(up, r);
					down = Math.max(down, r);
				}
			}
		}

		for (int r = up; r <= down; r++) {
			for (int c = left; c <= right; c++) {
				output.append(map[r][c]);
			}
			output.append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String str = input.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}

		solution();

		System.out.println(output);
	}
}
