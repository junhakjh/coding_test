import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] infos;

	static int solution() {
		int answer = 0;

		Map<Double, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
		int up = 0, down = 0;
		for (int[] info : infos) {
			if (info[0] == 0) {
				if (info[1] > 0) {
					up++;
					answer = Math.max(answer, up);
				} else {
					down++;
					answer = Math.max(answer, down);
				}
				continue;
			}
			double num = (double) info[1] / (double) info[0];
			Map<Double, Integer> map = (info[0] > 0 ? map1 : map2);
			map.putIfAbsent(num, 0);
			map.put(num, map.get(num) + 1);
			answer = Math.max(answer, map.get(num));
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		infos = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			infos[i][0] = Integer.parseInt(st.nextToken());
			infos[i][1] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}
}
