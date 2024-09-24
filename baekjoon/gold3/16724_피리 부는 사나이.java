import java.util.*;
import java.io.*;

class Union {
	int[] parents;

	Union(int n) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = -1;
		}
	}

	int findRoot(int i) {
		if (parents[i] < 0) {
			return i;
		}
		return parents[i] = findRoot(parents[i]);
	}

	boolean union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		if (aRoot == bRoot) {
			return false;
		}
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	static int n, m;
	static int[][] map;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m) {
			return false;
		}
		return true;
	}

	static int solution() {
		int answer = 0;

		Union union = new Union(n * m);

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (map[r][c] != -1) {
					Queue<int[]> q = new ArrayDeque<>();
					q.offer(new int[] { r, c, map[r][c] });
					map[r][c] = -1;
					int root = r * m + c;

					while (!q.isEmpty()) {
						int[] item = q.poll();
						int cr = item[0], cc = item[1], i = item[2];
						int nr = cr + dr[i], nc = cc + dc[i];
						if (isIn(nr, nc)) {
							if (map[nr][nc] != -1) {
								q.offer(new int[] { nr, nc, map[nr][nc] });
								map[nr][nc] = -1;
								union.union(root, nr * m + nc);
							} else {
								if (!union.union(root, nr * m + nc)) {
									answer++;
								}
							}
						}
					}
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int r = 0; r < n; r++) {
			String str = input.readLine();
			for (int c = 0; c < m; c++) {
				switch (str.charAt(c)) {
				case 'U':
					map[r][c] = 0;
					break;
				case 'D':
					map[r][c] = 1;
					break;
				case 'L':
					map[r][c] = 2;
					break;
				case 'R':
					map[r][c] = 3;
					break;
				}
			}
		}

		System.out.println(solution());
	}
}
