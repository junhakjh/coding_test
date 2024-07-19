import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int[] dx = { 1, 0, -1, 0 };
	static final int[] dy = { 0, 1, 0, -1 };
	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visited;
	static int answer = 0;

	static void dfs(int x, int y, int depth, int sum) {
		if(depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= m || ny < 0 || ny >= n || visited[ny][nx]) continue;
			visited[ny][nx] = true;
			dfs(nx, ny, depth + 1, sum + map[ny][nx]);
			visited[ny][nx] = false;
		}
	}
	
	static void tSearch(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int sum = map[y][x];
			for(int j = 0; j < 3; j++) {
				int nx = x + dx[(i + j) % 4];
				int ny = y + dy[(i + j) % 4];
				if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
				sum += map[ny][nx];
			}
			answer = Math.max(answer,  sum);
		}
	}

	static int solution() {
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				visited[y][x] = true;
				dfs(x, y, 0, 0);
				visited[y][x] = false;
				tSearch(x, y);
			}
		}
		
		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];

		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(input.readLine());
			for (int x = 0; x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
	}
}
