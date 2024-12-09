import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int N, H, D;
	static char[][] map;
	static int sr, sc;
	static int answer = Integer.MAX_VALUE;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}
		return true;
	}

	static void solution() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { sr, sc, H, 0, 0 });
		int[][] visited = new int[N][N];
		visited[sr][sc] = H;
		while (!q.isEmpty()) {
			int[] item = q.poll();
			int r = item[0], c = item[1], cHealth = item[2], cUmbrella = item[3], num = item[4];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (isIn(nr, nc)) {
					if (map[nr][nc] == 'E') { // 안전지대 도착했을 때
						answer = Math.min(answer, num + 1);
						continue;
					}
					int health = cHealth, umbrella = cUmbrella;
					if (map[nr][nc] == 'U') {
						umbrella = D;
					}
					if (umbrella == 0) {
						health--;
					} else {
						umbrella--;
					}
					if (health == 0) {
						continue;
					}
					if (visited[nr][nc] < health + umbrella) {
						visited[nr][nc] = health + umbrella;
						q.offer(new int[] { nr, nc, health, umbrella, num + 1 });
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		for (int r = 0; r < N; r++) {
			String str = input.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == 'S') {
					sr = r;
					sc = c;
				}
			}
		}

		solution();

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
}
