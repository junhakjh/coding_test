import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int N, W, H;
	static int[][] blocks;
	static int[] tops;
	static int answer;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= H || c < 0 || c >= W) {
			return false;
		}
		return true;
	}

	static int[][] copyBlocks(int[][] map) {
		int[][] copy = new int[H][W];
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				copy[r][c] = map[r][c];
			}
		}

		return copy;
	}

	static void getTops(int[][] curBlocks) {
		label: for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (curBlocks[r][c] > 0) {
					tops[c] = r;
					continue label;
				}
			}
			tops[c] = H;
		}
	}

	static int getBlocks(int[][] curBlocks) {
		int n = 0;
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (curBlocks[r][c] > 0) {
					n += (H - r);
					break;
				}
			}
		}

		return n;
	}

	static void refresh(int[][] curBlocks) {
		for (int c = 0; c < W; c++) {
			int prevR = H;
			for (int r = H - 1; r >= 0; r--) {
				if (curBlocks[r][c] != 0) {
					int temp = curBlocks[r][c];
					curBlocks[r][c] = 0;
					curBlocks[prevR - 1][c] = temp;
					prevR = prevR - 1;
				}
			}
		}
	}

	static void destroy(int startC, int depth, int[][] curBlocks) {
		if (depth == N) {
			answer = Math.min(answer, getBlocks(curBlocks));
			return;
		}
		if (answer == 0) {
			return;
		}

		boolean check = false;
		for (int i = 0; i < W; i++) {
			getTops(curBlocks);
			if (tops[i] == H) {
				continue;
			}
			check = true;
			int[][] newBlocks = copyBlocks(curBlocks);
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] { tops[i], i, newBlocks[tops[i]][i] });
			newBlocks[tops[i]][i] = 0;
			while (!q.isEmpty()) {
				int[] item = q.poll();
				int r = item[0], c = item[1], power = item[2];
				for (int d = 0; d < 4; d++) {
					for (int p = 1; p < power; p++) {
						int nr = r + dr[d] * p, nc = c + dc[d] * p;
						if (isIn(nr, nc)) {
							if (newBlocks[nr][nc] > 1) {
								q.offer(new int[] { nr, nc, newBlocks[nr][nc] });
							}
							newBlocks[nr][nc] = 0;
						}
					}
				}
			}
			refresh(newBlocks);
			destroy(i, depth + 1, newBlocks);
		}
		if (!check) {
			answer = 0;
			return;
		}
	}

	static int solution() {
		answer = Integer.MAX_VALUE;

		getTops(blocks);

		for (int i = 0; i < N; i++) {
			destroy(i, 0, blocks);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			blocks = new int[H][W];
			tops = new int[W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < W; c++) {
					blocks[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
