import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M, L;
	static int[] cake, nums;

	static void solution() {
		for (int num : nums) {
			int l = 0, r = L;
			int max = 0;
			while (l <= r) {
				int unit = (l + r) / 2;
				int cut = 0;
				int prev = 0;
				for (int position : cake) {
					if (position - prev >= unit) {
						prev = position;
						cut++;
					}
				}
				if (cut <= num) {
					r = unit - 1;
				} else {
					max = Math.max(max, unit);
					l = unit + 1;
				}
			}
			output.append(max).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		cake = new int[M + 1];
		cake[M] = L;
		nums = new int[N];
		for (int i = 0; i < M; i++) {
			cake[i] = Integer.parseInt(input.readLine());
		}
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(input.readLine());
		}

		solution();

		System.out.println(output);
	}
}
