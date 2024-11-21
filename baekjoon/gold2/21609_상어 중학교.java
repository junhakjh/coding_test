import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };
	static final int BLACK = -1;
	static final int RAINBOW = 0;
	static final int EMPTY = -2;

	static int N, M;
	static int[][] blocks;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}
		return true;
	}

	static int[][] rotate(int[][] map) {
		int[][] result = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				result[N - c - 1][r] = map[r][c];
			}
		}
		return result;
	}

	static void down(int[][] map) {
		for (int c = 0; c < N; c++) {
			int prevR = N;
			for (int r = N - 1; r >= 0; r--) {
				if (map[r][c] == BLACK) {
					prevR = r;
				} else if (map[r][c] >= 0) {
					if (r != prevR - 1) {
						map[prevR - 1][c] = map[r][c];
						map[r][c] = EMPTY;
					}
					prevR--;
				}
			}
		}
	}

	static int solution() {
		int answer = 0;

		boolean[][] visited;

		while (true) {
			visited = new boolean[N][N];
			int normal = 0, rainbow = 0, score = 0;
			List<int[]> toEmpty = new ArrayList<>();
			boolean check = false;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && blocks[r][c] > 0) {
						int cnormal = 0, crainbow = 0, color = blocks[r][c];
						List<int[]> candidate = new ArrayList<>();
						Queue<int[]> q = new ArrayDeque<>();
						q.offer(new int[] { r, c });
						visited[r][c] = true;
						while (!q.isEmpty()) {
							int[] item = q.poll();
							int curR = item[0], curC = item[1];
							if (blocks[curR][curC] == RAINBOW) {
								crainbow++;
							} else {
								cnormal++;
							}
							candidate.add(new int[] { curR, curC });
							for (int i = 0; i < 4; i++) {
								int nr = curR + dr[i], nc = curC + dc[i];
								if (isIn(nr, nc) && !visited[nr][nc]
										&& (blocks[nr][nc] == color || blocks[nr][nc] == RAINBOW)) {
									visited[nr][nc] = true;
									q.offer(new int[] { nr, nc });
								}
							}
						}
						if (cnormal + crainbow > 1) {
							check = true;
							if (cnormal + crainbow > normal + rainbow) {
								normal = cnormal;
								rainbow = crainbow;
								score = (normal + rainbow) * (normal + rainbow);
								toEmpty = candidate;
							} else if (cnormal + crainbow == normal + rainbow) {
								if (crainbow >= rainbow) {
									normal = cnormal;
									rainbow = crainbow;
									score = (normal + rainbow) * (normal + rainbow);
									toEmpty = candidate;
								}
							}
						}
					}
					for (int rr = 0; rr < N; rr++) {
						for (int cc = 0; cc < N; cc++) {
							if (blocks[rr][cc] == RAINBOW) {
								visited[rr][cc] = false;
							}
						}
					}
				}
			}
			if (check) {
				answer += score;
				for (int[] coor : toEmpty) {
					int r = coor[0], c = coor[1];
					blocks[r][c] = EMPTY;
				}
				down(blocks);
				blocks = rotate(blocks);
				down(blocks);
			} else {
				break;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		blocks = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				blocks[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
	}
}
