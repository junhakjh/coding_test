
import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int max, min;
	static int n;
	static int[] calc = new int[4], nums;
	static int[] arr;

	static void comb(int num, int curI) {
		if (curI == n) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (calc[i] > 0) {
				calc[i]--;
				switch (i) {
				case 0:
					comb(num + nums[curI], curI + 1);
					break;
				case 1:
					comb(num - nums[curI], curI + 1);
					break;
				case 2:
					comb(num * nums[curI], curI + 1);
					break;
				case 3:
					comb(num / nums[curI], curI + 1);
					break;
				}
				calc[i]++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			n = Integer.parseInt(input.readLine());
			arr = new int[n - 1];
			nums = new int[n];
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < 4; i++) {
				calc[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			comb(nums[0], 1);

			output.append("#").append(tc).append(" ").append(max - min).append("\n");
		}

		System.out.print(output);
	}

}
