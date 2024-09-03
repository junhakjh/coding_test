import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 3.
 * @link https://www.acmicpc.net/problem/1600
 * @timecomplex
 * @performance 61,800 kb, 476 ms
 * @category #bfs
 * @note
 */

class Monkey {
	int r;
	int c;
	int k;
	int n;

	Monkey(int r, int c, int k, int n) {
		this.r = r;
		this.c = c;
		this.k = k;
		this.n = n;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] hdr = { 1, 2, 2, 1, -1, -2, -2, -1 }, hdc = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	static int k, h, w;
	static boolean[][][] map;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= h || c < 0 || c >= w) {
			return false;
		}
		return true;
	}

	static int solution() {
		int answer = -1;
		if (h == 1 && w == 1) {
			return 0;
		}

		Queue<Monkey> q = new ArrayDeque<>();
		q.offer(new Monkey(0, 0, k, 0));
		label: while (!q.isEmpty()) {
			Monkey monkey = q.poll();
			if (monkey.k > 0) {
				for (int i = 0; i < 8; i++) {
					int nr = monkey.r + hdr[i], nc = monkey.c + hdc[i];
					if (isIn(nr, nc) && !map[monkey.k - 1][nr][nc]) {
						map[monkey.k - 1][nr][nc] = true;
						if (nr == h - 1 && nc == w - 1) {
							answer = monkey.n + 1;
							break label;
						}
						q.offer(new Monkey(nr, nc, monkey.k - 1, monkey.n + 1));
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				int nr = monkey.r + dr[i], nc = monkey.c + dc[i];
				if (isIn(nr, nc) && !map[monkey.k][nr][nc]) {
					map[monkey.k][nr][nc] = true;
					if (nr == h - 1 && nc == w - 1) {
						answer = monkey.n + 1;
						break label;
					}
					q.offer(new Monkey(nr, nc, monkey.k, monkey.n + 1));
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		k = Integer.parseInt(input.readLine());
		st = new StringTokenizer(input.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new boolean[k + 1][h][w];
		for (int r = 0; r < h; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < w; c++) {
				boolean state = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				for (int i = 0; i <= k; i++) {
					map[i][r][c] = state;
				}
			}
		}
		for (int i = 0; i <= k; i++) {
			map[i][0][0] = true;
		}

		System.out.println(solution());
	}
}
