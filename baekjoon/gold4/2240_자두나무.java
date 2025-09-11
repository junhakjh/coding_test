import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int T, W;
	static int[] infos;

	static int solution() {
		int answer = 0;

		int[][] dp = new int[T + 1][W + 1];
		for (int t = 1; t <= T; t++) {
			int tree = infos[t];
			for (int w = 0; w <= W; w++) {
				if (w % 2 == 0) { // 왼쪽
					if (tree == 0) { // 현재 위치에 자두 떨어질 때
						dp[t][w] = dp[t - 1][w] + 1;
					} else { // 옆에 자두 떨어질 때
						if (w != 0) {
							dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
						}
						dp[t][w] = Math.max(dp[t][w], dp[t - 1][w]);
					}
				} else { // 오른쪽
					if (tree == 1) { // 현재 위치에 자두 떨어질 때
						dp[t][w] = dp[t - 1][w] + 1;
					} else { // 옆에 자두 떨어질 때
						if (w != 0) {
							dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
						}
						dp[t][w] = Math.max(dp[t][w], dp[t - 1][w]);
					}
				}
			}
		}

		for (int num : dp[T]) {
			answer = Math.max(answer, num);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		infos = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			infos[i] = Integer.parseInt(input.readLine()) - 1;
		}

		System.out.println(solution());
	}
}
