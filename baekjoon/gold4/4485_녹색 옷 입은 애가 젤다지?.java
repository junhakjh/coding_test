import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 3.
 * @link https://www.acmicpc.net/problem/4485
 * @timecomplex
 * @performance 18,712 kb, 188 ms
 * @category #다익스트라 #우선순위큐
 * @note
 */

class Edge implements Comparable<Edge> {
	int r;
	int c;
	int w;

	Edge(int r, int c, int w) {
		this.r = r;
		this.c = c;
		this.w = w;
	}

	@Override
	public int compareTo(Edge e) {
		return Integer.compare(this.w, e.w);
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	static int n;
	static int[][] map;
	static int[][] weights;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n) {
			return false;
		}
		return true;
	}

	static int solution() {
		Queue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(0, 0, weights[0][0]));
		while (!q.isEmpty()) {
			Edge edge = q.poll();
			int r = edge.r, c = edge.c, w = edge.w;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (isIn(nr, nc)) {
					int newW = w + map[nr][nc];
					if (newW < weights[nr][nc]) {
						weights[nr][nc] = newW;
						q.offer(new Edge(nr, nc, newW));
					}
				}
			}
		}

		return weights[n - 1][n - 1];
	}

	public static void main(String[] args) throws Exception {
		int t = 0;
		while (true) {
			t++;
			n = Integer.parseInt(input.readLine());
			if (n == 0) {
				break;
			}
			map = new int[n][n];
			weights = new int[n][n];
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					weights[r][c] = Integer.MAX_VALUE;
				}
			}
			weights[0][0] = map[0][0];

			output.append("Problem ").append(t).append(": ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
