import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] arr;

	static int count(int k) {
		int cnt = 0, rest = 0;

		for (int money : arr) {
			if (money > k) {
				return M + 1;
			}
			if (money > rest) {
				cnt++;
				rest = k;
			}
			rest -= money;
		}

		return cnt;
	}

	static int solution() {
		int l = 1, r = 1_000_000_000;
		while (l < r) {
			int k = (l + r) / 2;
			int cnt = count(k);
			if (cnt > M) {
				l = k + 1;
			} else {
				r = k;
			}
		}

		return l;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input.readLine());
		}

		System.out.println(solution());
	}
}
