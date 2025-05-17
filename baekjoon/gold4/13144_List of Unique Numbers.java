import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] arr;
	static Map<Integer, Integer> position = new HashMap<>();;;

	static long solution() {
		long answer = 1;
		position.put(arr[0], 0);
		int lastIdx = -1;

		for (int i = 1; i < N; i++) {
			int num = arr[i];
			if (position.containsKey(num)) {
				lastIdx = Math.max(position.get(num), lastIdx);
			}
			position.put(num, i);
			answer += (i - lastIdx);
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
