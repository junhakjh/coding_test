import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 3.
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu
 * @timecomplex
 * @performance 27,092 kb, 191 ms
 * @category #완전탐색 #누적합
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static boolean[][] map;

	static int answer;
	static int maxProfit;

	static void solution() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				int[] houses = new int[2 * n - 1];
				for (int nr = 0; nr < n; nr++) {
					for (int nc = 0; nc < n; nc++) {
						if (map[nr][nc]) {
							houses[Math.abs(nr - r) + Math.abs(nc - c)] += 1;
						}
					}
				}
				for (int i = 1; i < houses.length; i++) {
					houses[i] += houses[i - 1];
				}
				int num = 0;
				for (int i = 0; i < houses.length; i++) {
					int profit = houses[i] * m - ((i + 1) * (i + 1) + i * i);
					if (profit >= 0) {
						num = houses[i];
					}
				}
				if (num > answer) {
					answer = num;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			maxProfit = Integer.MIN_VALUE;
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new boolean[n][n];
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				}
			}

			solution();

			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(output);
	}
}
