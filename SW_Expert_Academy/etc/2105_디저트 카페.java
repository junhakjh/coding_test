import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 27.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV5VwAr6APYDFAWu&probBoxId=AZGNXHWapmQDFAQP&type=PROBLEM&problemBoxTitle=0826%EC%A3%BC&problemBoxCnt=5
 * @timecomplex 
 * @performance 50,096 kb, 381 ms
 * @category #dfs
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 1, -1, -1 }, dc = { 1, -1, -1, 1 };

	static int n;
	static int[][] map;
	static boolean[][] visited;
	static Map<Integer, List<int[]>> numMapper;
	static int startR, startC;

	static int answer;

	static boolean check(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n) {
			return false;
		}
		if (visited[r][c])
			return false;

		return true;
	}

	static void dfs(int r, int c, int di, int cnt) {

		if (r == startR && c == startC && cnt != 0) {
			answer = Math.max(answer, cnt);
			return;
		}

		int nr = r + dr[di], nc = c + dc[di];
		if (check(nr, nc)) {
			for (int[] coor : numMapper.get(map[nr][nc])) {
				visited[coor[0]][coor[1]] = true;
			}
			dfs(nr, nc, di, cnt + 1);
			for (int[] coor : numMapper.get(map[nr][nc])) {
				visited[coor[0]][coor[1]] = false;
			}
		}
		nr = r + dr[(di + 1) % 4];
		nc = c + dc[(di + 1) % 4];
		if (check(nr, nc)) {
			for (int[] coor : numMapper.get(map[nr][nc])) {
				visited[coor[0]][coor[1]] = true;
			}
			dfs(nr, nc, (di + 1) % 4, cnt + 1);
			for (int[] coor : numMapper.get(map[nr][nc])) {
				visited[coor[0]][coor[1]] = false;
			}
		}
	}

	static void solution() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (r + c == 0 || r + c == 2 * n - 2 || Math.abs(r - c) == n - 1) {
					continue;
				}
				startR = r;
				startC = c;
				for (int[] coor : numMapper.get(map[r][c])) {
					visited[coor[0]][coor[1]] = true;
				}
				for (int y = 0; y < n; y++) {
					for (int x = 0; x < n; x++) {
						if (x + y < r + c || y - x < r - c) {
							visited[y][x] = true;
						}
					}
				}
				visited[r][c] = false;
				dfs(r, c, 0, 0);
				for (int[] coor : numMapper.get(map[r][c])) {
					visited[coor[0]][coor[1]] = false;
				}
				for (int y = 0; y < n; y++) {
					for (int x = 0; x < n; x++) {
						if (x + y < r + c || y - x < r - c) {
							visited[y][x] = false;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = -1;
			numMapper = new HashMap<>();
			n = Integer.parseInt(input.readLine());
			map = new int[n][n];
			visited = new boolean[n][n];
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					int num = Integer.parseInt(st.nextToken());
					map[r][c] = num;
					if (!numMapper.containsKey(num)) {
						numMapper.put(num, new ArrayList<>());
					}
					numMapper.get(num).add(new int[] { r, c });
				}
			}

			solution();

			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(output);
	}
}
