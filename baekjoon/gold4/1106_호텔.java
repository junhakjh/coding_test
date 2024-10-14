import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int C, N;
	static int[][] infos;
	static int[] costs;

	static int solution() {
		int answer = Integer.MAX_VALUE;

		for (int[] info : infos) {
			int cost = info[0], num = info[1];
			for (int i = 0; i < C; i++) {
				if (costs[i] != Integer.MAX_VALUE) {
					costs[i + num] = Math.min(costs[i + num], costs[i] + cost);
				}
			}
		}

		for (int i = C; i < C + 100; i++) {
			answer = Integer.min(answer, costs[i]);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		infos = new int[N][2];
		costs = new int[C + 100];
		for (int i = 0; i < C + 100; i++) {
			costs[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			infos[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			costs[infos[i][1]] = Math.min(costs[infos[i][1]], infos[i][0]);
		}

		System.out.println(solution());
	}

}
