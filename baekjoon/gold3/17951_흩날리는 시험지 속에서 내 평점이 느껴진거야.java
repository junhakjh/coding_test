import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, K;
	static int[] scores;
	static int scoreSum;

	static int solution() {
		int max = 0;
		int l = 0, r = scoreSum / K;
		while (l <= r) {
			int mid = (l + r) / 2;
			int sum = 0, cut = 0, min = scoreSum;
			for (int score : scores) {
				sum += score;
				if (sum >= mid) {
					cut++;
					min = Math.min(min, sum);
					sum = 0;
				}
			}
			if (cut < K) {
				r = mid - 1;
			} else {
				l = mid + 1;
				max = Math.max(max, min);
			}
		}

		return max;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		scores = new int[N];
		st = new StringTokenizer(input.readLine());
		scoreSum = 0;
		for (int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
			scoreSum += scores[i];
		}

		System.out.println(solution());
	}
}
