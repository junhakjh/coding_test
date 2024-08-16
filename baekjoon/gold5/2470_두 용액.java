import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 16.
 * @link https://www.acmicpc.net/problem/2470
 * @timecomplex O(nlog(n))
 * @performance 28,152 kb, 316 ms
 * @category #투 포인터
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[] nums;

	static void solution() {
		int[] answer = new int[2];
		int minSum = Integer.MAX_VALUE, sum;
		int l = 0, r = n - 1;
		while (l < r) {
			sum = nums[l] + nums[r];
			if (sum == 0) {
				output.append(nums[l]).append(" ").append(nums[r]);
				return;
			}
			if (Math.abs(minSum) > Math.abs(sum)) {
				minSum = sum;
				answer[0] = nums[l];
				answer[1] = nums[r];
			}
			if (sum > 0) {
				r--;
			} else {
				l++;
			}
		}
		output.append(answer[0]).append(" ").append(answer[1]);
	}

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(input.readLine());
		st = new StringTokenizer(input.readLine());
		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		solution();

		System.out.print(output);
	}
}
