import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static final int[][][] cctvDirs = { { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 2 }, { 1, 3 } },
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } },
			{ { 0, 1, 2, 3 } } };

	static int N, M;
	static int[][] map;
	static List<int[]> cctvList;
	static int answer = Integer.MAX_VALUE;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static void dfs(int cctvIdx) {
		if (cctvIdx == cctvList.size()) {
			int sum = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 0) {
						sum++;
					}
				}
			}
			answer = Math.min(answer, sum);
			return;
		}

		int[] coor = cctvList.get(cctvIdx);
		int cctvCheckNum = (cctvIdx + 1) * 10;
		int cr = coor[0], cc = coor[1];
		for (int[] cctvDir : cctvDirs[map[cr][cc] - 1]) {
			for (int di : cctvDir) {
				int r = cr, c = cc;
				while (isIn(r, c)) {
					if (map[r][c] == 0) {
						map[r][c] = cctvCheckNum;
					}
					if (map[r][c] == 6) {
						break;
					}
					r += dr[di];
					c += dc[di];
				}
			}
			dfs(cctvIdx + 1);
			for (int di : cctvDir) {
				int r = cr, c = cc;
				while (isIn(r, c)) {
					if (map[r][c] == cctvCheckNum) {
						map[r][c] = 0;
					}
					if (map[r][c] == 6) {
						break;
					}
					r += dr[di];
					c += dc[di];
				}
			}
		}
	}

	static int solution() {
		dfs(0);

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] >= 1 && map[r][c] <= 5) {
					cctvList.add(new int[] { r, c });
				}
			}
		}

		System.out.println(solution());
	}
}
