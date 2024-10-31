import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] arr;

	static int solution() {
		int answer = Integer.MAX_VALUE;

		Arrays.sort(arr);
		for (int i = 0; i < N - 3; i++) {
			for (int j = i + 3; j < N; j++) {
				int asnow = arr[i] + arr[j];
				int l = i + 1, r = j - 1;
				while (l < r) {
					int bsnow = arr[l] + arr[r];
					int diff = asnow - bsnow;
					answer = Math.min(answer, Math.abs(diff));
					if (diff > 0) {
						l++;
					} else {
						r--;
					}
				}
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
