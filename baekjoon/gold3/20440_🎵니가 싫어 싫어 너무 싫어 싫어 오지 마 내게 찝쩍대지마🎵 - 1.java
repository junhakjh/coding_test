import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] times;
	static Map<Integer, Integer> timesMap;

	static void solution() {
		int maxMos = 0, l = 0, r = 0;

		List<Integer> keySetList = new ArrayList<>(timesMap.keySet());
		Collections.sort(keySetList);
		int prevSum = 0;
		boolean isMax = false;
		for (int key : keySetList) {
			if (timesMap.get(key) == 0) {
				continue;
			}
			if(isMax && timesMap.get(key) < 0) {
				r = key;
			}
			prevSum += timesMap.get(key);
			if (prevSum > maxMos) {
				maxMos = prevSum;
				l = key;
				isMax = true;
			} else {
				isMax = false;
			}
		}

		output.append(maxMos).append("\n").append(l).append(" ").append(r).append("\n");
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		times = new int[N][2];
		timesMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
			timesMap.put(start, timesMap.getOrDefault(start, 0) + 1);
			timesMap.put(end, timesMap.getOrDefault(end, 0) - 1);
		}

		solution();

		System.out.println(output);
	}
}
