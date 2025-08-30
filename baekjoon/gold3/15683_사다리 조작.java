import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, 0 }, dc = { 0, -1, 1 };
	static final int DOWN = 0, LEFT = 1, RIGHT = 2;

	static int N, M, H;
	static int[][] map;
	static int answer = 4;

	static boolean checkAvailable() {
		for (int i = 0; i < N; i++) {
			int r = 0, c = i, prevC = i;
			while (r < H) {
				int dir = map[r][c];
				if (dir != DOWN && c + dc[dir] == prevC) {
					prevC = c;
					r += dr[DOWN];
					c += dc[DOWN];
				} else {
					prevC = c;
					r += dr[dir];
					c += dc[dir];
				}
			}
			if (c != i) {
				return false;
			}
		}

		return true;
	}

	static void dfs(int cr, int cc, int cnt) {
		if (cr == H - 1 && cc == N - 1) {
			if (checkAvailable()) {
				answer = Math.min(answer, cnt);
			}
			return;
		}

		int nr = cc == N - 1 ? cr + 1 : cr, nc = cc == N - 1 ? 0 : cc + 1;

		// 가로선 3개 미만이고, 오른쪽에 세로선 하나 더 있고, 현재 위치와 다음 위치에 가로선이 없을 때 가로선 추가
		if (cnt < 3 && cc < N - 1 && map[cr][cc] == DOWN && map[cr][cc + 1] == DOWN) {
			map[cr][cc] = RIGHT;
			map[cr][cc + 1] = LEFT;
			dfs(nr, nc, cnt + 1);
			map[cr][cc] = DOWN;
			map[cr][cc + 1] = DOWN;
		}
		// 가로선 추가 X
		dfs(nr, nc, cnt);
	}

	static int solution() {
		dfs(0, 0, 0);

		return answer == 4 ? -1 : answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = RIGHT;
			map[a][b + 1] = LEFT;
		}

		System.out.println(solution());
	}
}
