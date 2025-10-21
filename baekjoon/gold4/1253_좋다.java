import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] arr;

	static int solution() {
		int answer = 0;

		Arrays.sort(arr);
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			int target = arr[i];
			if (set.contains(target)) {
				answer++;
				continue;
			}
			int l = 0, r = N - 1;
			while (l < r) {
				if (l == i) {
					l++;
					continue;
				}
				if (r == i) {
					r--;
					continue;
				}
				int sum = arr[l] + arr[r];
				if (sum > target) {
					r--;
				} else if (sum < target) {
					l++;
				} else {
					set.add(target);
					answer++;
					break;
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
