import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int N, M;
	static int[][] map;
	static int[][][] timeMap;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static void dfs(int r, int c, int t, int bridge) {
		if (r == N - 1 && c == N - 1) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i], nc = c + dc[i];
			if (isIn(nr, nc)) {
				if (map[nr][nc] == 1 && timeMap[nr][nc][bridge] > t + 1) {
					timeMap[nr][nc][bridge] = t + 1;
					dfs(nr, nc, t + 1, bridge);
				}
				if (map[nr][nc] >= 2) {
					int nnr = nr + dr[i], nnc = nc + dc[i], nt = t + (map[nr][nc] - t % map[nr][nc]) + 1;
					// 오작교 넘어갈 때, 다다음으로 넘어갈 수 있는지 확인
					if (isIn(nnr, nnc) && map[nnr][nnc] == 1 && timeMap[nnr][nnc][bridge] > nt) {
						timeMap[nnr][nnc][bridge] = nt;
						dfs(nnr, nnc, nt, bridge);
					}
				}
				if (map[nr][nc] == 0 && bridge == 0) {
					int nnr = nr + dr[i], nnc = nc + dc[i], nt = t + (M - t % M);
					// 오작교 넘어갈 때, 다다음으로 넘어갈 수 있는지 확인
					if ((!isIn(nnr, nnc) || map[nnr][nnc] == 1) && timeMap[nr][nc][1] > nt) {
						timeMap[nr][nc][1] = nt;
						dfs(nr, nc, nt, 1);
					}
				}
			}
		}
	}

	static int solution() {
		timeMap = new int[N][N][2];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				timeMap[r][c][0] = Integer.MAX_VALUE;
				timeMap[r][c][1] = Integer.MAX_VALUE;
			}
		}
		timeMap[0][0][0] = 0;
		timeMap[0][0][1] = 0;

		dfs(0, 0, 0, 0);

		return Math.min(timeMap[N - 1][N - 1][0], timeMap[N - 1][N - 1][1]);
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
	}
}
