import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 13.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV14ABYKADACFAYh&probBoxId=AZFIwxLqKmIDFAQW&type=PROBLEM&problemBoxTitle=0812%EC%A3%BC&problemBoxCnt=3
 * @timecomplex O(n)
 * @performance 30,236 kb, 160 ms
 * @category #탐색
 * @note
 */

class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	static int[][] ladder;
	static int start;

	static int solution() {
		int c = start;
		int r = 99;
		boolean[][] visited = new boolean[100][100];
		while (r >= 0) {
			visited[r][c] = true;
			if (c > 0 && !visited[r][c - 1] && ladder[r][c - 1] == 1) {
				c--;
				continue;
			}
			if (c < 99 && !visited[r][c + 1] && ladder[r][c + 1] == 1) {
				c++;
				continue;
			}
			r--;
		}

		return c;
	}

	public static void main(String args[]) throws Exception {
		for (int tc = 1; tc <= 10; tc++) {
			input.readLine();
			ladder = new int[100][100];
			for (int r = 0; r < 100; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < 100; c++) {
					ladder[r][c] = Integer.parseInt(st.nextToken());
					if (ladder[r][c] == 2) {
						start = c;
					}
				}
			}
			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
