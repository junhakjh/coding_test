import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, K;
	static int[] belt;

	static int solution() {
		int phase = 0, startI = 0;
		int num = 0;
		boolean[] robots = new boolean[N];

		label: while (true) {
			phase++;
			if (--startI < 0) {
				startI += 2 * N;
			}
			for (int i = N - 2; i >= 0; i--) {
				if (robots[i]) {
					robots[i] = false;
					robots[i + 1] = true;
				}
			}
			robots[N - 1] = false;

			for (int i = startI + N - 2; i >= startI; i--) {
				int beltI = i % (2 * N);
				int nextBeltI = (beltI + 1) % (2 * N);
				int robotI = i - startI;
				if (belt[nextBeltI] > 0) {
					if (robots[robotI] && !robots[robotI + 1]) {
						robots[robotI] = false;
						robots[robotI + 1] = true;
						belt[nextBeltI] -= 1;
						if (belt[nextBeltI] == 0) {
							num++;
							if (num == K) {
								break label;
							}
						}
					}
				}
				if (i == startI && belt[beltI] > 0) {
					robots[robotI] = true;
					belt[beltI] -= 1;
					if (belt[beltI] == 0) {
						num++;
						if (num == K) {
							break label;
						}
					}
				}
			}
		}

		return phase;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[2 * N];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}
}
