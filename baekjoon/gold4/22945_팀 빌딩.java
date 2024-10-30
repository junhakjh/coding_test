import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] arr;

	static int solution() {
		int answer = 0;

		int l = 0, r = N - 1;
		while (l < r) {
			if (arr[l] < arr[r]) {
				answer = Math.max(answer, (r - l - 1) * arr[l]);
				l++;
			} else {
				answer = Math.max(answer, (r - l - 1) * arr[r]);
				r--;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		arr = new int[N];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}
}
