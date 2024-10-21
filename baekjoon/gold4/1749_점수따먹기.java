import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[][] map;

	static int solution() {
		int answer = Integer.MIN_VALUE;

		for (int r2 = 0; r2 < N; r2++) {
			for (int c2 = 0; c2 < M; c2++) {
				for (int r1 = 0; r1 <= r2; r1++) {
					for (int c1 = 0; c1 <= c2; c1++) {
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
						answer = Math.max(answer, area);
					}
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

		;

		System.out.println(solution());
	}
}
