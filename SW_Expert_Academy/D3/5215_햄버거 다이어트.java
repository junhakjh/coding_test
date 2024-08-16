import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 16.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AWT-lPB6dHUDFAVT&probBoxId=AZFIwxLqKmIDFAQW&type=PROBLEM&problemBoxTitle=0812%EC%A3%BC&problemBoxCnt=7
 * @timecomplex O(2^n)
 * @performance 20,336 kb, 948 ms
 * @category #완전탐색, 부분집합
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, l;
	static int[][] ingredients;

	static int solution() {
		int answer = 0;

		for (int b = 1; b < 1 << n; b++) {
			int score = 0, cal = 0;
			boolean check = false;
			for (int i = 0; i < n; i++) {
				int temp = 1 << i;
				if ((b & temp) == temp) {
					score += ingredients[i][0];
					cal += ingredients[i][1];
					if (cal > l) {
						check = true;
						break;
					}
				}
			}
			if (!check) {
				answer = Math.max(answer, score);
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			ingredients = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(input.readLine());
				ingredients[i][0] = Integer.parseInt(st.nextToken());
				ingredients[i][1] = Integer.parseInt(st.nextToken());
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
