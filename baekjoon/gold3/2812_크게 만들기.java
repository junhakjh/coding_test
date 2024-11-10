import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, K;

	static void solution(String str) {
		char[] stack = new char[N];
		stack[0] = str.charAt(0);
		int pointer = 1, k = K;
		for (int i = 1; i < N; i++) {
			char c = str.charAt(i);
			while (k > 0 && pointer > 0 && stack[pointer - 1] < c) {
				k--;
				pointer--;
			}
			stack[pointer++] = c;
		}
		for (int i = 0; i < N - K; i++) {
			output.append(stack[i]);
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		solution(input.readLine());

		System.out.println(output);
	}
}
