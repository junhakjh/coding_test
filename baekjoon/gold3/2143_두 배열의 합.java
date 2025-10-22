import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int T, N, M;
	static int[] a, b;

	static long solution() {
		long answer = 0;

		Map<Integer, Integer> aCnt = new HashMap<>();
		for (int i = 0; i <= N - 1; i++) {
			for (int j = i + 1; j <= N; j++) {
				int num = a[j] - a[i];
				aCnt.putIfAbsent(num, 0);
				aCnt.put(num, aCnt.get(num) + 1);
			}
		}

		for (int i = 0; i <= M - 1; i++) {
			for (int j = i + 1; j <= M; j++) {
				int num = b[j] - b[i];
				int aNum = T - num;
				if (aCnt.containsKey(aNum)) {
					answer += aCnt.get(aNum);
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(input.readLine());
		N = Integer.parseInt(input.readLine());
		a = new int[N + 1];
		st = new StringTokenizer(input.readLine());
		for (int i = 1; i <= N; i++) {
			a[i] = a[i - 1] + Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(input.readLine());
		b = new int[M + 1];
		st = new StringTokenizer(input.readLine());
		for (int i = 1; i <= M; i++) {
			b[i] = b[i - 1] + Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}
}
