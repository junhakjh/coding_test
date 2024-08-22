import java.util.*;
import java.io.*;

/*
@author 김준하
@since 2024.08.22
@link https://www.acmicpc.net/problem/3109
@timecomplex
@performance 29,140 kb, 348 ms
@category #dfs, #백트래킹, #그리디
@note
*/

public class Algorithm {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 0, 1 }, dc = { 1, 1, 1 };

	static int answer = 0;
	static int R, C;
	static boolean[][] map;
	static boolean flag;

	static void dfs(int r, int c, int testR) {
		if (c == C - 1) {
			answer++;
			flag = true;
			return;
		}
		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i], nc = c + dc[i];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && !map[nr][nc]) {
				map[nr][nc] = true;
				dfs(nr, nc, testR);
				if (flag) {
					return;
				}
			}
		}
	}

	static void solution() {
		for (int startR = 0; startR < R; startR++) {
			flag = false;
			dfs(startR, 0, startR);
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(input.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String str = input.readLine();
			for (int c = 0; c < C; c++) {
				if (str.charAt(c) == '.') {
					map[r][c] = false;
				} else {
					map[r][c] = true;
				}
			}
		}

		solution();

		System.out.println(answer);
	}
}
