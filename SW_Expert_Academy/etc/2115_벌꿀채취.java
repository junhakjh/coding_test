import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 2.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV5V4A46AdIDFAWu&probBoxId=AZGv0iC6KcYDFAQP&type=PROBLEM&problemBoxTitle=0902%EC%A3%BC&problemBoxCnt=1
 * @timecomplex
 * @performance 18,680 kb, 130 ms
 * @category #완전탐색 #부분집합
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m, c;
	static int[][] map;
	static boolean[] visited;
	static int[] nums;
	static int maxSum;

	static int answer;

	static void getSum(int depth, int sum, int powSum) {
		if (depth == m) {
			maxSum = Math.max(maxSum, powSum);
			return;
		}

		if (sum + nums[depth] <= c) {
			visited[depth] = true;
			getSum(depth + 1, sum + nums[depth], powSum + nums[depth] * nums[depth]);
		}
		visited[depth] = false;
		getSum(depth + 1, sum, powSum);
	}

	static void dfs(int depth, int startI, int sum) {
		if (depth == 2) {
			answer = Math.max(answer, sum);
			return;
		}

		for (int id = startI; id < n * n; id++) {
			int r = id / n, c = id % n;
			if (c + m - 1 >= n) {
				continue;
			}
			visited = new boolean[m];
			for (int i = 0; i < m; i++) {
				nums[i] = map[r][c + i];
			}
			maxSum = 0;
			getSum(0, 0, 0);
			dfs(depth + 1, id + m, sum + maxSum);
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			nums = new int[m];
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 0, 0);

			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(output);
	}
}
