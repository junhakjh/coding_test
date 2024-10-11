import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
	int e1;
	int e2;
	int w;

	Edge(int e1, int e2, int w) {
		this.e1 = e1;
		this.e2 = e2;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(w, o.w);
	}
}

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

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int N, M;
	static boolean[][] inputmap;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M) {
			return false;
		}
		return true;
	}

	static void setIsland(int num, int[][] map, int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		map[r][c] = num;
		while (!q.isEmpty()) {
			int[] pos = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = pos[0] + dr[i], nc = pos[1] + dc[i];
				if (isIn(nr, nc) && inputmap[nr][nc] && map[nr][nc] == 0) {
					map[nr][nc] = num;
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	static int solution() {
		int answer = 0;

		int[][] map = new int[N][M];
		int n = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (inputmap[r][c] && map[r][c] == 0) {
					setIsland(n++, map, r, c);
				}
			}
		}

		Queue<Edge> pq = new PriorityQueue<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] > 0) {
					int islandNum = map[r][c];
					for (int i = 0; i < 4; i++) {
						int sr = r, sc = c;
						int nr = r + dr[i], nc = c + dc[i];
						while (isIn(nr, nc)) {
							if (map[nr][nc] == islandNum) {
								sr = nr;
								sc = nc;
							} else if (map[nr][nc] > 0) {
								int dist = Math.abs(nr - sr) + Math.abs(nc - sc) - 1;
								if (dist > 1) {
									pq.offer(new Edge(islandNum, map[nr][nc], dist));
								}
								break;
							}
							nr += dr[i];
							nc += dc[i];
						}
					}
				}
			}
		}

		Union union = new Union(n);
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union.union(edge.e1, edge.e2)) {
				answer += edge.w;
			}
		}
		int rootNum = 0;
		for (int i = 1; i < n; i++) {
			union.findRoot(i);
		}
		for (int i = 1; i < n; i++) {
			if (union.parents[i] < 0) {
				rootNum++;
			}
		}

		return answer == 0 || rootNum > 1 ? -1 : answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inputmap = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				inputmap[r][c] = Integer.parseInt(st.nextToken()) == 0 ? false : true;
			}
		}

		System.out.println(solution());
	}

}
