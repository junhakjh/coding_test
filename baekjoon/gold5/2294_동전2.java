import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, k;
	static int[] coins, nums;

	static int solution() {
		for (int coin : coins) {
			for (int i = coin; i <= k; i++) {
				if (nums[i - coin] != Integer.MAX_VALUE) {
					nums[i] = Math.min(nums[i], nums[i - coin] + 1);
				}
			}
		}

		return nums[k] == Integer.MAX_VALUE ? -1 : nums[k];
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coins = new int[n];
		nums = new int[k + 1];
		for (int i = 0; i <= k; i++) {
			nums[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(input.readLine());
			if (coins[i] <= k) {
				nums[coins[i]] = 1;
			}
		}

		System.out.println(solution());
	}

}
