import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	static int N, M, K;
	static int[][] map, players;
	static int[] exit;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static int getDi(int r, int c) {
		if (r > exit[0] && map[r - 1][c] <= 0) {
			return 0;
		}
		if (r < exit[0] && map[r + 1][c] <= 0) {
			return 1;
		}
		if (c > exit[1] && map[r][c - 1] <= 0) {
			return 2;
		}
		if (c < exit[1] && map[r][c + 1] <= 0) {
			return 3;
		}
		return -1;
	}

	static int move() {
		int moves = 0;
		int[][] newPlayers = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (players[r][c] == 0) {
					continue;
				}
				int di = getDi(r, c);
				if (di == -1) {
					newPlayers[r][c] += players[r][c];
					continue;
				}
				int nr = r + dr[di], nc = c + dc[di];
				moves += players[r][c];
				if (map[nr][nc] == 0) { // 출구가 아니고 이동할 수 있는 공간이라면 이동
					newPlayers[nr][nc] += players[r][c];
				}
			}
		}

		players = newPlayers;

		return moves;
	}

	static void rotateMap(int sr, int sc, int L) {
		int[][] newMap = new int[N][N], newPlayers = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				newMap[r][c] = map[r][c];
				newPlayers[r][c] = players[r][c];
			}
		}

		for (int r = sr; r < sr + L; r++) {
			for (int c = sc; c < sc + L; c++) {
				int dr = r - sr, dc = c - sc;
				newMap[r][c] = map[sr + ((L - 1) - dc)][sc + dr];
				if (newMap[r][c] > 0) {
					newMap[r][c]--;
				}
				newPlayers[r][c] = players[sr + ((L - 1) - dc)][sc + dr];
				if (newMap[r][c] == -1) {
					exit = new int[] { r, c };
				}
			}
		}

		map = newMap;
		players = newPlayers;
	}

	static boolean rotate() {
		Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
			int aLen = Math.max(Math.abs(exit[0] - a[0]), Math.abs(exit[1] - a[1])),
					bLen = Math.max(Math.abs(exit[0] - b[0]), Math.abs(exit[1] - b[1]));
			if (aLen == bLen) {
				int asr = Math.max(0, Math.max(a[0], exit[0]) - aLen),
						bsr = Math.max(0, Math.max(b[0], exit[0]) - bLen);
				if (asr == bsr) {
					int asc = Math.max(0, Math.max(a[1], exit[1]) - aLen),
							bsc = Math.max(0, Math.max(b[1], exit[1]) - bLen);
					return Integer.compare(asc, bsc);
				}
				return Integer.compare(asr, bsr);
			}
			return Integer.compare(aLen, bLen);
		});

		int r = -1, c = -1;
		boolean[][] visited = new boolean[N][N];
		pq.offer(exit);
		visited[exit[0]][exit[1]] = true;
		while (!pq.isEmpty()) {
			int[] item = pq.poll();
			if (players[item[0]][item[1]] > 0) {
				r = item[0];
				c = item[1];
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nr = item[0] + dr[i], nc = item[1] + dc[i];
				if (isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					pq.offer(new int[] { nr, nc });
				}
			}
		}

		if (r == -1 && c == -1) {
			return false;
		}

		int L = Math.max(Math.abs(exit[0] - r), Math.abs(exit[1] - c));
		int sr = Math.max(0, Math.max(r, exit[0]) - L), sc = Math.max(0, Math.max(c, exit[1]) - L);

		rotateMap(sr, sc, L + 1);

		return true;
	}

	static void solution() {
		int moves = 0;
		for (int k = 0; k < K; k++) {
			moves += move();
			if (!rotate()) {
				break;
			}
		}

		output.append(moves).append("\n").append(exit[0] + 1).append(" ").append(exit[1] + 1);
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		players = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			players[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]++;
		}
		st = new StringTokenizer(input.readLine());
		exit = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
		map[exit[0]][exit[1]] = -1;

		solution();

		System.out.println(output);
	}
}
