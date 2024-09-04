import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static double solution(int n, int k, int l, Queue<Integer> hams, int[] colas) {
		double answer = 0;

		int c = 0;
		Queue<Integer> cq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			c += colas[i];
			if (i - l >= 0) {
				c -= colas[i - l];
			}
			cq.offer(c);
		}

		while (!hams.isEmpty()) {
			answer += Math.floor(hams.poll() / Math.pow(2, cq.poll()));
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		Queue<Integer> hams = new PriorityQueue<>();
		int[] colas = new int[n + 1];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			hams.offer(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < k; i++) {
			colas[Integer.parseInt(st.nextToken()) - 1]++;
		}

		System.out.printf("%.0f", solution(n, k, l, hams, colas));
	}
}
