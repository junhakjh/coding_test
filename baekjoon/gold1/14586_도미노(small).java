import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] dominos;

	static int leftDfs(int idx, long minH, int[] memo) {
		minH = Math.min(minH, (long) dominos[idx][0] - (long) dominos[idx][1]);
		if (idx == 0 || dominos[idx - 1][0] < minH) {
			return memo[idx] = idx;
		}
		return memo[idx] = leftDfs(idx - 1, minH, memo);
	}

	static int rightDfs(int idx, long maxH, int[] memo) {
		maxH = Math.max(maxH, (long) dominos[idx][0] + (long) dominos[idx][1]);
		if (idx == N - 1 || dominos[idx + 1][0] > maxH) {
			return memo[idx] = idx;
		}
		return memo[idx] = rightDfs(idx + 1, maxH, memo);
	}

	static int solution() {
		Arrays.sort(dominos, (a, b) -> Integer.compare(a[0], b[0]));
		int[] leftMemo = new int[N], rightMemo = new int[N];
		for (int i = 0; i < N; i++) {
			rightDfs(i, 0l, rightMemo);
			leftDfs(N - 1 - i, 2_000_000_000l, leftMemo);
		}

		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			if (leftMemo[i] - 1 < 0) {
				dp[i] = 1;
			} else {
				dp[i] = dp[leftMemo[i] - 1] + 1;
			}
			for (int j = 0; j < i; j++) {
				if (rightMemo[j] >= i) {
					if (j == 0) {
						dp[i] = 1;
					} else {
						dp[i] = Math.min(dp[i], dp[j - 1] + 1);
					}
				}
			}
		}
		return dp[N - 1];
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		dominos = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			dominos[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}

		System.out.println(solution());
	}
}
