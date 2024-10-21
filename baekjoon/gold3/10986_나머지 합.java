import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static long N, M;
	static Map<Long, Long> mods;

	static long solution() {
		long answer = 0;

		for (long value : mods.values()) {
			answer += (value * (value - 1)) / 2;
		}
		answer += mods.getOrDefault((long) 0, (long) 0);

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		mods = new HashMap<>();
		st = new StringTokenizer(input.readLine());
		long prev = 0;
		for (int i = 0; i < N; i++) {
			prev = (prev + Long.parseLong(st.nextToken()) % M) % M;
			mods.putIfAbsent(prev, (long) 0);
			mods.put(prev, mods.get(prev) + 1);
		}

		System.out.println(solution());
	}
}
