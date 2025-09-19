import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[] cross, left, right;

	static void solution() {
		long[][] dp = new long[2][n];
		dp[0][0] = 0;
		dp[1][0] = cross[0];
		int crossIdx = 0;
		for (int i = 1; i < n; i++) {
			dp[0][i] = dp[0][i - 1] + left[i - 1];
			long a = dp[0][i] + cross[i], b = dp[1][i - 1] + right[i - 1];
			if (a < b) {
				crossIdx = i;
				dp[1][i] = a;
			} else {
				dp[1][i] = b;
			}
		}

		output.append(crossIdx + 1).append(" ").append(dp[1][n - 1]);
	}

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(input.readLine());
		cross = new int[n];
		left = new int[n - 1];
		right = new int[n - 1];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			cross[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < n - 1; i++) {
			left[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < n - 1; i++) {
			right[i] = Integer.parseInt(st.nextToken());
		}

		solution();

		System.out.println(output);
	}
}
