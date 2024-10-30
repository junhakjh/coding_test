import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, K;
	static int[] arr;
	static Map<Integer, Integer> map;

	static int solution() {
		int answer = 0;

		int l = 0;
		for (int i = 0; i < N; i++) {
			int num = arr[i];
			map.put(num, map.getOrDefault(num, 0) + 1);
			if (map.get(num) > K) {
				while (l < i) {
					int lNum = arr[l];
					map.put(lNum, map.get(lNum) - 1);
					l++;
					if (lNum == num) {
						break;
					}
				}
			}
			answer = Math.max(answer, i - l);
		}

		return answer + 1;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		map = new HashMap<>();
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}
}
