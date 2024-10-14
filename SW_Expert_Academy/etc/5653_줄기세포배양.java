import java.io.*;
import java.util.*;

class Cell {
	int st;
	int life;
	boolean alive;

	Cell(int st, int life) {
		this.st = st;
		this.life = life;
		this.alive = true;
	}
}

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int N, M, K, R, C;
	static Cell[][] map;

	static boolean canGo(int nr, int nc, int r, int c, int time) {
		// 번식하려는 위치에 죽어있는 세포 있는경우
		if (!map[nr][nc].alive) {
			return false;
		}
		// 번식하려는 위치에 세포 없는 경우
		if (map[nr][nc].life == 0) {
			return true;
		}
		// 번식하려는 위치에 세포 있고, 같은 시간에 번식 시도해서 생명력으로 이기는 경우
		if (map[nr][nc].st == time && map[nr][nc].life < map[r][c].life) {
			return true;
		}
		return false;
	}

	static int solution() {
		int answer = 0;

		Set<Integer> set = new HashSet<>();
		for (int r = K; r < N + K; r++) {
			for (int c = K; c < M + K; c++) {
				if (map[r][c].life > 0) {
					set.add(r * C + c);
				}
			}
		}

		for (int t = 0; t < K; t++) {
			Set<Integer> newSet = new HashSet<>();
			for (int id : set) {
				int r = id / C, c = id % C;
				// 활성화 된 순간
				if (map[r][c].st + map[r][c].life == t) {
					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i], nc = c + dc[i];
						if (canGo(nr, nc, r, c, t + 1)) {
							map[nr][nc] = new Cell(t + 1, map[r][c].life);
							map[r][c].alive = false;
							newSet.add(nr * C + nc);
						}
					}
				} else {
					newSet.add(r * C + c);
				}
			}
			set = newSet;
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c].st + map[r][c].life * 2 > K) {
					answer++;
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			R = N + K * 2;
			C = M + K * 2;
			map = new Cell[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					map[r][c] = new Cell(0, 0);
				}
			}
			for (int r = K; r < N + K; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = K; c < M + K; c++) {
					map[r][c].life = Integer.parseInt(st.nextToken());
				}
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
