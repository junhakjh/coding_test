import java.util.*;
import java.io.*;

class Stuff {
	int w;
	int v;

	Stuff(int w, int v) {
		this.w = w;
		this.v = v;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, k;
	static Stuff[] bag;

	static int solution() {
		int answer = 0;

		int[] dp = new int[k + 1];

		for (Stuff stuff : bag) {
			for (int i = k; i >= stuff.w; i--) {
				dp[i] = Math.max(dp[i], dp[i - stuff.w] + stuff.v);
				answer = Math.max(answer, dp[i]);
			}
		}

		return answer;

	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		bag = new Stuff[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(input.readLine());
			bag[i] = new Stuff(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		System.out.println(solution());
	}

}
