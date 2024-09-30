import java.util.*;
import java.io.*;

class App {
	int w;
	int v;

	App(int w, int v) {
		this.w = w;
		this.v = v;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static App[] apps;

	static int solution() {
		int answer = Integer.MAX_VALUE;

		int[] dp = new int[n * 100 + 1];

		for (App app : apps) {
			for (int i = n * 100; i >= app.v; i--) {
				dp[i] = Math.max(dp[i], dp[i - app.v] + app.w);
				if (dp[i] >= m) {
					answer = Math.min(answer, i);
				}
			}
		}

		return answer;

	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		apps = new App[n];
		StringTokenizer memoriesSt = new StringTokenizer(input.readLine());
		StringTokenizer costsSt = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			apps[i] = new App(Integer.parseInt(memoriesSt.nextToken()), Integer.parseInt(costsSt.nextToken()));
		}

		System.out.println(solution());
	}

}
