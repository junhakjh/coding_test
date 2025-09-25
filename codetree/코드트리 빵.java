import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int[] dr = { -1, 0, 0, 1 }, dc = { 0, -1, 1, 0 };

	static int N, M;
	static int[][] map, marts;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static int solution() {
		int[][] wallMap = new int[N][N];
		int t = 1;
		Queue<int[]> q = new ArrayDeque<>();
		do {
			Queue<int[]> nq = new ArrayDeque<>();
			while (!q.isEmpty()) {
				int[][] track = new int[N][N];
				int[] item = q.poll();
				int sr = item[0], sc = item[1], martIdx = item[2], mr = marts[martIdx][0], mc = marts[martIdx][1];
				Queue<int[]> dq = new ArrayDeque<>();
				dq.offer(new int[] { sr, sc });
				boolean[][] visited = new boolean[N][N];
				visited[sr][sc] = true;
				label: while (!dq.isEmpty()) {
					int[] cur = dq.poll();
					for (int i = 0; i < 4; i++) {
						int nr = cur[0] + dr[i], nc = cur[1] + dc[i];
						if (isIn(nr, nc) && !visited[nr][nc] && (wallMap[nr][nc] == 0 || wallMap[nr][nc] >= t)) {
							visited[nr][nc] = true;
							track[nr][nc] = (3 - i);
							if (nr == mr && nc == mc) {
								break label;
							}
							dq.offer(new int[] { nr, nc });
						}
					}
				}
				int r = mr, c = mc;
				while (!(r == sr && c == sc)) {
					int nr = r + dr[track[r][c]], nc = c + dc[track[r][c]];
					if (nr == sr && nc == sc) {
						break;
					}
					r = nr;
					c = nc;
				}
				if (r == mr && c == mc) {
					wallMap[r][c] = t;
				} else {
					nq.offer(new int[] { r, c, martIdx });
				}
			}
			if (t <= M) {
				int martR = marts[t - 1][0], martC = marts[t - 1][1];
				boolean[][] visited = new boolean[N][N];
				visited[martR][martC] = true;
				Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
					if (a[2] == b[2]) {
						if (a[0] == b[0]) {
							return Integer.compare(a[1], b[1]);
						}
						return Integer.compare(a[0], b[0]);
					}
					return Integer.compare(a[2], b[2]);
				});
				pq.offer(new int[] { martR, martC, 0 });
				while (!pq.isEmpty()) {
					int[] item = pq.poll();
					if (map[item[0]][item[1]] == 1) {
						wallMap[item[0]][item[1]] = t;
						nq.offer(new int[] { item[0], item[1], t - 1 });
						break;
					}
					for (int i = 0; i < 4; i++) {
						int nr = item[0] + dr[i], nc = item[1] + dc[i];
						if (isIn(nr, nc) && !visited[nr][nc] && wallMap[nr][nc] == 0) {
							visited[nr][nc] = true;
							pq.offer(new int[] { nr, nc, item[2] + 1 });
						}
					}
				}
			}
			q = nq;
			t++;
		} while (!q.isEmpty());

		return t - 1;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		marts = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken()) - 1;
			marts[i] = new int[] { r, c };
		}

		System.out.println(solution());
	}
}
