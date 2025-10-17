import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] arr;
	static Map<Integer, Integer> map;

	static String solution() {
		int min = 9 * M / 10 + (9 * M % 10 == 0 ? 0 : 1);
		for (int i = 0; i < N; i++) {
			if (i >= M) {
				map.put(arr[i - M], map.get(arr[i - M]) - 1);
			}
			map.put(arr[i], map.get(arr[i]) + 1);
			if (map.get(arr[i]) >= min) {
				return "YES";
			}
		}

		return "NO";
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		map = new HashMap<>();
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			map.put(arr[i], 0);
		}

		System.out.println(solution());
	}
}
