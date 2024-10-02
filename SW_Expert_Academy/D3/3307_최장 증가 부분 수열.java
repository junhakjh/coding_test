import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[] arr;

	static int solution() {
		List<Integer> dp = new ArrayList<>();
		dp.add(arr[0]);
		for (int num : arr) {
			int idx = Collections.binarySearch(dp, num);
			if (idx < 0) {
				idx = (-1) * idx - 1;
			}
			if(idx == dp.size()) {
				dp.add(num);
			} else {
				dp.set(idx, num);
			}
		}

		return dp.size();
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(input.readLine());
			arr = new int[n];
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
