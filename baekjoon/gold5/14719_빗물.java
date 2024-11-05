import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int H, W;
	static int[] blocks;

	static int solution() {
		int answer = 0;

		int[] rains = new int[W];
		int max = 0;
		for (int i = 0; i < W; i++) {
			max = Math.max(max, blocks[i]);
			rains[i] = max;
		}
		max = 0;
		for (int i = W - 1; i >= 0; i--) {
			max = Math.max(max, blocks[i]);
			rains[i] = Math.min(max, rains[i]);
		}
		for (int i = 0; i < W; i++) {
			answer += rains[i] - blocks[i];
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		blocks = new int[W];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < W; i++) {
			blocks[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}
}
