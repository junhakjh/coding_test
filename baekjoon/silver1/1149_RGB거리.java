import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 4.
 * @link https://www.acmicpc.net/problem/1149
 * @timecomplex
 * @performance 18,476 kb, 116 ms
 * @category #dp
 * @note
 */

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int solution(int n, int[][] map) {
		for (int r = 1; r < n; r++) {
			for (int i = 0; i < 3; i++) {
				map[r][i] += Math.min(map[r - 1][(i + 1) % 3], map[r - 1][(i + 2) % 3]);
			}
		}

		return Math.min(map[n - 1][0], Math.min(map[n - 1][1], map[n - 1][2]));
	}

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(input.readLine());
		int[][] map = new int[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < 3; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution(n, map));
	}
}
