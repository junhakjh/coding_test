import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 4.
 * @link https://www.acmicpc.net/problem/1463
 * @timecomplex
 * @performance 18,152 kb, 140 ms
 * @category #dp
 * @note
 */

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int solution(int target) {
		int[] dp = new int[target + 1];
		for (int i = 1; i <= target; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[1] = 0;
		for (int i = 1; i <= target - 1; i++) {
			dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
			if (i * 2 <= target) {
				dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
			}
			if (i * 3 <= target) {
				dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
			}
		}

		return dp[target];

//		Queue<int[]> q = new PriorityQueue<>((o1, o2) -> {
//			if (o1[1] == o2[1]) {
//				return (-1) * Integer.compare(o1[0], o2[0]);
//			} else {
//				return Integer.compare(o1[1], o2[1]);
//			}
//		});
//		q.offer(new int[] { 1, 0 });
//		while (!q.isEmpty()) {
//			int[] item = q.poll();
//			int num = item[0], cnt = item[1];
//
//			if (num + 1 == target)
//				return cnt + 1;
//			q.offer(new int[] { num + 1, cnt + 1 });
//			if (num * 2 <= target) {
//				if (num * 2 == target) {
//					return cnt + 1;
//				}
//				q.offer(new int[] { num * 2, cnt + 1 });
//			}
//			if (num * 3 <= target) {
//				if (num * 3 == target) {
//					return cnt + 1;
//				}
//				q.offer(new int[] { num * 3, cnt + 1 });
//			}
//		}
//
//		return -1;
	}

	public static void main(String[] args) throws Exception {
		int num = Integer.parseInt(input.readLine());

		System.out.println(solution(num));
	}
}
