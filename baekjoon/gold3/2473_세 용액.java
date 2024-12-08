import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static long[] liquid;

	static int binarySearch(int sl, int sr, long sum) {
		long min = Long.MAX_VALUE;
		int l = sl, r = sr;
		int mid = (l + r) / 2;
		int result = mid;
		while (l <= r) {
			long curSum = sum + liquid[mid];
			long sumAbs = Math.abs(curSum);
			if (sumAbs < min) {
				result = mid;
				min = sumAbs;
			}
			if (curSum < 0) {
				l = mid + 1;
			} else if (curSum > 0) {
				r = mid - 1;
			} else {
				break;
			}
			mid = (l + r) / 2;
		}

		return result;
	}

	static void solution() {
		long min = Long.MAX_VALUE;

		for (int pivot1 = 0; pivot1 < N - 2; pivot1++) {
			for (int pivot2 = pivot1 + 1; pivot2 < N - 1; pivot2++) {
				int l = pivot2 + 1, r = N - 1;
				int i = binarySearch(l, r, liquid[pivot1] + liquid[pivot2]);
				long sumAbs = Math.abs(liquid[pivot1] + liquid[pivot2] + liquid[i]);
				if (sumAbs < min) {
					output = new StringBuilder();
					output.append(liquid[pivot1]).append(" ").append(liquid[pivot2]).append(" ").append(liquid[i]);
					min = sumAbs;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		liquid = new long[N];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			liquid[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(liquid);

		solution();

		System.out.println(output);
	}
}
