import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] nums;

	static int solution() {
		int answer = 0;

		int[] maxSums = new int[N];
		for (int i = 0; i < N; i++) {
			int max = nums[i];
			for (int j = i; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					max = Math.max(max, nums[i] + maxSums[j]);
				}
			}
			maxSums[i] = max;
			answer = Math.max(answer, max);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		nums = new int[N];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}

}
