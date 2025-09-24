import java.util.*;
import java.io.*;

class Soldier {
	int r, c, h, w, initLife, life;

	Soldier(int r, int c, int h, int w, int life) {
		this.r = r;
		this.c = c;
		this.h = h;
		this.w = w;
		this.initLife = life;
		this.life = life;
	}

	boolean isAlive() {
		return life > 0;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	static int L, N, Q;
	static int[][] map, soldierMap, orders;
	static Soldier[] soldiers;

	static boolean canGo(int r, int c) {
		return r >= 0 && r < L && c >= 0 && c < L && map[r][c] != 2;
	}

	static boolean canPush(int soldierIdx, int di) {
		List<int[]> list = new ArrayList<>();
		Soldier soldier = soldiers[soldierIdx];
		if (di == 0) {
			for (int c = soldier.c; c < soldier.c + soldier.w; c++) {
				int nr = soldier.r + dr[di], nc = c + dc[di];
				if (!canGo(nr, nc)) {
					return false;
				}
				list.add(new int[] { nr, nc });
			}
		} else if (di == 1) {
			for (int r = soldier.r; r < soldier.r + soldier.h; r++) {
				int nr = r + dr[di], nc = soldier.c + soldier.w - 1 + dc[di];
				if (!canGo(nr, nc)) {
					return false;
				}
				list.add(new int[] { nr, nc });
			}
		} else if (di == 2) {
			for (int c = soldier.c; c < soldier.c + soldier.w; c++) {
				int nr = soldier.r + soldier.h - 1 + dr[di], nc = c + dc[di];
				if (!canGo(nr, nc)) {
					return false;
				}
				list.add(new int[] { nr, nc });
			}
		} else if (di == 3) {
			for (int r = soldier.r; r < soldier.r + soldier.h; r++) {
				int nr = r + dr[di], nc = soldier.c + dc[di];
				if (!canGo(nr, nc)) {
					return false;
				}
				list.add(new int[] { nr, nc });
			}
		}

		boolean[] visited = new boolean[N + 1];
		visited[0] = true;
		for (int[] item : list) {
			int r = item[0], c = item[1];
			if (visited[soldierMap[r][c]]) {
				continue;
			}
			visited[soldierMap[r][c]] = true;
			if (!canPush(soldierMap[r][c], di)) {
				return false;
			}
		}

		return true;
	}

	static void push(int initSoldierIdx, int soldierIdx, int di) {
		List<int[]> list = new ArrayList<>();
		Soldier soldier = soldiers[soldierIdx];
		if (di == 0) {
			for (int c = soldier.c; c < soldier.c + soldier.w; c++) {
				int nr = soldier.r + dr[di], nc = c + dc[di];
				if (!canGo(nr, nc)) {
				}
				list.add(new int[] { nr, nc });
			}
		} else if (di == 1) {
			for (int r = soldier.r; r < soldier.r + soldier.h; r++) {
				int nr = r + dr[di], nc = soldier.c + soldier.w - 1 + dc[di];
				if (!canGo(nr, nc)) {
				}
				list.add(new int[] { nr, nc });
			}
		} else if (di == 2) {
			for (int c = soldier.c; c < soldier.c + soldier.w; c++) {
				int nr = soldier.r + soldier.h - 1 + dr[di], nc = c + dc[di];
				if (!canGo(nr, nc)) {
				}
				list.add(new int[] { nr, nc });
			}
		} else if (di == 3) {
			for (int r = soldier.r; r < soldier.r + soldier.h; r++) {
				int nr = r + dr[di], nc = soldier.c + dc[di];
				if (!canGo(nr, nc)) {
				}
				list.add(new int[] { nr, nc });
			}
		}

		boolean[] visited = new boolean[N + 1];
		visited[0] = true;
		for (int[] item : list) {
			int r = item[0], c = item[1];
			if (visited[soldierMap[r][c]]) {
				continue;
			}
			visited[soldierMap[r][c]] = true;
			push(initSoldierIdx, soldierMap[r][c], di);
		}

		for (int r = soldier.r; r < soldier.r + soldier.h; r++) {
			for (int c = soldier.c; c < soldier.c + soldier.w; c++) {
				soldierMap[r][c] = 0;
			}
		}
		soldier.r += dr[di];
		soldier.c += dc[di];
		for (int r = soldier.r; r < soldier.r + soldier.h; r++) {
			for (int c = soldier.c; c < soldier.c + soldier.w; c++) {
				soldierMap[r][c] = soldierIdx;
				if (initSoldierIdx != soldierIdx && map[r][c] == 1) {
					soldier.life--;
				}
			}
		}
	}

	static void deleteSoldiers() {
		for (int r = 0; r < L; r++) {
			for (int c = 0; c < L; c++) {
				if (soldierMap[r][c] != 0 && !soldiers[soldierMap[r][c]].isAlive()) {
					soldierMap[r][c] = 0;
				}
			}
		}
	}

	static int solution() {
		int answer = 0;

		for (int[] order : orders) {
			int soldierIdx = order[0], di = order[1];
			if (!soldiers[soldierIdx].isAlive()) {
				continue;
			}
			int[][] prevSoldierMap = new int[L][L];
			for (int r = 0; r < L; r++) {
				for (int c = 0; c < L; c++) {
					prevSoldierMap[r][c] = soldierMap[r][c];
				}
			}
			if (canPush(soldierIdx, di)) {
				push(soldierIdx, soldierIdx, di);
			}
			deleteSoldiers();
		}

		for (Soldier soldier : soldiers) {
			if (soldier != null && soldier.isAlive()) {
				answer += soldier.initLife - soldier.life;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new int[L][L];
		for (int r = 0; r < L; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < L; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		soldiers = new Soldier[N + 1];
		soldierMap = new int[L][L];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(input.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1, sc = Integer.parseInt(st.nextToken()) - 1,
					h = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken()),
					k = Integer.parseInt(st.nextToken());
			soldiers[i] = new Soldier(sr, sc, h, w, k);
			for (int r = sr; r < sr + h; r++) {
				for (int c = sc; c < sc + w; c++) {
					soldierMap[r][c] = i;
				}
			}
		}
		orders = new int[Q][2];
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(input.readLine());
			orders[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}

		System.out.println(solution());
	}
}
