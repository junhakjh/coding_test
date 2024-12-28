import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static long maxTime;
	static long[] times;

	static long solution() {
		long l = 0, r = maxTime, result = 0;
		while (l <= r) {
			result = (l + r) / 2;
			if (l == r) {
				break;
			}
			long balloons = 0;
			for (long time : times) {
				balloons += result / time;
			}
			if (balloons >= M) {
				r = result;
			} else {
				l = result + 1;
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		times = new long[N];
		maxTime = 0;
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(st.nextToken());
			maxTime = Math.max(maxTime, times[i] * M);
		}

		System.out.println(solution());
	}
}
