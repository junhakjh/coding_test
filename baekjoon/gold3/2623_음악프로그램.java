import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] in;
	static Map<Integer, List<Integer>> orders;

	static boolean solution() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if (in[i] == 0) {
				q.offer(i);
			}
		}
		int num = 0;
		while (!q.isEmpty()) {
			int i = q.poll();
			num++;
			output.append(i + 1).append("\n");
			for (int next : orders.get(i)) {
				if (--in[next] == 0) {
					q.offer(next);
				}
			}
		}
		return num == N ? true : false;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		in = new int[N];
		orders = new HashMap<>();
		for (int i = 0; i < N; i++) {
			orders.put(i, new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int K = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken()) - 1;
			for(int j = 0; j < K - 1; j++) {
				int next = Integer.parseInt(st.nextToken()) - 1;
				orders.get(prev).add(next);
				in[next]++;
				prev = next;
			}
		}

		System.out.println(solution() ? output : 0);
	}
}
