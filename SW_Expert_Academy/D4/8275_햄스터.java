import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 14.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AWxQ310aOlQDFAWL&probBoxId=AZFIwxLqKmIDFAQW&type=PROBLEM&problemBoxTitle=0812%EC%A3%BC&problemBoxCnt=4
 * @timecomplex
 * @performance
 * @category #
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static void solution(int n, int x, int m, int[][] infos) {
		int[] answer = { -1 };

		int[] arr = new int[n];
		int[] sums = new int[n]; // i번째 원소까지의 합

		int answerSum = -1;

		while (arr[0] != x + 1) {
			int totalSum = 0;
			for (int i = 0; i < n; i++) {
				totalSum += arr[i];
				sums[i] = totalSum;
			}

			boolean check = false;
			for (int[] info : infos) {
				int l = info[0] - 1, r = info[1] - 1, s = info[2];
				int sum = sums[r];
				if (l != 0) {
					sum -= sums[l - 1];
				}
				if (sum != s) {
					check = true;
					break;
				}
			}
			if (!check) {
				if (answerSum < sums[n - 1]) {
					answerSum = sums[n - 1];
					answer = arr.clone();
				}
			}

			arr[n - 1]++;
			for (int i = n - 1; i > 0; i--) {
				if (arr[i] > x) {
					arr[i] = 0;
					arr[i - 1]++;
				} else {
					break;
				}
			}
		}
		for (int val : answer) {
			output.append(val);
			output.append(" ");
		}
		output.append("\n");
	}

	public static void main(String[] args) throws Exception {
		int t = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(input.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			int[][] infos = new int[m][3];
			for (int r = 0; r < m; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < 3; c++) {
					infos[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			output.append("#").append(tc).append(" ");
			solution(n, x, m, infos);

		}
		System.out.print(output);
	}
}
