import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static String str;

	static int solution() {
		int answer = 0;

		int[] states = new int[81];
		states[0] = 1;
		int t = 0, g = 0, f = 0, p = 0;
		for (int i = 0; i < N; i++) {
			char c = str.charAt(i);
			switch (c) {
			case 'T':
				t = (t + 1) % 3;
				break;
			case 'G':
				g = (g + 1) % 3;
				break;
			case 'F':
				f = (f + 1) % 3;
				break;
			case 'P':
				p = (p + 1) % 3;
				break;
			}
			int state = t + 3 * g + 9 * f + 27 * p;
			answer += states[state]++;
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		str = input.readLine();

		System.out.println(solution());
	}
}
