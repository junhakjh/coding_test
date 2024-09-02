import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	static int h, w, k;
	static int[][] blocks;
	static boolean[][] map;

	static boolean check(int r, int c) {
		if (r < 0 || r >= h || c < 0 || c >= w) {
			return false;
		}
		return true;
	}

	static void solution() {
		for (int i = 0; i < k; i++) {
			for (int r = blocks[i][1]; r < blocks[i][3]; r++) {
				for (int c = blocks[i][0]; c < blocks[i][2]; c++) {
					map[r][c] = true;
				}
			}
		}

		int num = 0;
		Queue<Integer> pq = new PriorityQueue<>();
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (!map[r][c]) {
					num++;
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] { r, c });
					map[r][c] = true;
					int sum = 1;
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						for (int i = 0; i < 4; i++) {
							int nr = cur[0] + dr[i], nc = cur[1] + dc[i];
							if (check(nr, nc) && !map[nr][nc]) {
								sum++;
								map[nr][nc] = true;
								q.offer(new int[] { nr, nc });
							}
						}
					}
					pq.offer(sum);
				}
			}
		}

		output.append(num).append("\n");
		while (!pq.isEmpty()) {
			output.append(pq.poll()).append(" ");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		blocks = new int[k][4];
		map = new boolean[h][w];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(input.readLine());
			blocks[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}

		solution();

		System.out.println(output);
	}

}
