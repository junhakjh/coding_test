import java.io.*;
import java.util.*;

class Stuff {
	int v;
	int c;

	Stuff(int v, int c) {
		this.v = v;
		this.c = c;
	}
}

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, k;
	static Stuff[] bag;

	static int solution() {
		int answer = 0;

		int[] dp = new int[k + 1];
		for (Stuff stuff : bag) {
			for (int i = k; i >= stuff.v; i--) {
				dp[i] = Math.max(dp[i], dp[i - stuff.v] + stuff.c);
				answer = Math.max(answer, dp[i]);
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			bag = new Stuff[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(input.readLine());
				bag[i] = new Stuff(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		
		System.out.print(output);
	}
}
