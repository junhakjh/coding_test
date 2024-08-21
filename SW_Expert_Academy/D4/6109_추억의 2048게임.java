import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 21.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AWbrg9uabZsDFAWQ&probBoxId=AZFnt7l6FYcDFAQW&type=PROBLEM&problemBoxTitle=0819%EC%A3%BC&problemBoxCnt=8
 * @timecomplex O(n^2)
 * @performance 36,804 kb, 157 ms
 * @category #시뮬레이션
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, -1, 0, 1 };

	static int n;
	static String direction;
	static int[][] map;

	static void solution() {
		int i = 0, r = 0, c = 0;
		switch (direction) {
		case "up":
			i = 0;
			r = 0;
			c = 0;
			break;
		case "right":
			i = 1;
			r = 0;
			c = n - 1;
			break;
		case "down":
			i = 2;
			r = n - 1;
			c = 0;
			break;
		case "left":
			i = 3;
			r = 0;
			c = 0;
			break;
		}

		int prevR = r - dr[i], prevC = c - dc[i];
		boolean isPossible = false;
		while (r >= 0 && r < n && c >= 0 && c < n) {
			if (map[r][c] != 0) {
				if (isPossible && map[prevR][prevC] == map[r][c]) {
					map[prevR][prevC] *= 2;
					map[r][c] = 0;
					isPossible = false;
				} else if (!isPossible || map[prevR][prevC] != map[r][c]) {
					int temp = map[r][c];
					map[r][c] = 0;
					map[prevR += dr[i]][prevC += dc[i]] = temp;
					isPossible = true;
				}
			}

			r += dr[i];
			c += dc[i];
			if (r >= n || r < 0) {
				if (i == 0) {
					prevR = -1;
					r = 0;
				} else {
					prevR = n;
					r = n - 1;
				}
				prevC++;
				c++;
				isPossible = false;
			} else if (c >= n || c < 0) {
				if (i == 1) {
					prevC = n;
					c = n - 1;
				} else {
					prevC = -1;
					c = 0;
				}
				prevR++;
				r++;
				isPossible = false;
			}
		}
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				output.append(map[y][x]).append(" ");
			}
			output.append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			direction = st.nextToken();
			map = new int[n][n];
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			output.append("#").append(tc).append("\n");
			solution();
		}

		System.out.print(output);
	}
}
