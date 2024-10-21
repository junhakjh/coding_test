import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[][] map;
	static int[][] orders;

	static void solution() {
		for (int[] order : orders) {
			int r1 = order[0] - 1, c1 = order[1] - 1, r2 = order[2] - 1, c2 = order[3] - 1;
			int area = map[r2][c2];
			if (r1 > 0) {
				area -= map[r1 - 1][c2];
			}
			if (c1 > 0) {
				area -= map[r2][c1 - 1];
			}
			if (r1 > 0 && c1 > 0) {
				area += map[r1 - 1][c1 - 1];
			}

			output.append(area).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		orders = new int[M][4];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (r > 0) {
					map[r][c] += map[r - 1][c];
				}
				if (c > 0) {
					map[r][c] += map[r][c - 1];
				}
				if (r > 0 && c > 0) {
					map[r][c] -= map[r - 1][c - 1];
				}
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			orders[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}

		solution();

		System.out.print(output);
	}
}
