import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 4.
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
 * @timecomplex
 * @performance 31,904 kb, 183 ms
 * @category #dp
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, l;
	static int[] scores, cals;

	static int solution() {
		int answer = 0;

		int[] dp = new int[l + 1];
		for (int i = 1; i <= l; i++) {
			dp[i] = -1;
		}
		for (int i = 0; i < n; i++) {
			List<int[]> candidates = new ArrayList<>();
			for (int j = cals[i]; j <= l; j++) {
				if (dp[j - cals[i]] != -1) {
					if (dp[j] < dp[j - cals[i]] + scores[i]) {
						candidates.add(new int[] { j, dp[j - cals[i]] + scores[i] });
					}
				}
			}
			for (int[] candidate : candidates) {
				dp[candidate[0]] = candidate[1];
				answer = Math.max(answer, dp[candidate[0]]);
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
			scores = new int[n];
			cals = new int[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(input.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
