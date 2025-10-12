import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int X;
	static String str;

	static String solution() {
		List<String> list = new ArrayList<>();
		String next = str;
		do {
			list.add(next);
			if (list.size() - 1 == X) {
				return next;
			}
			StringBuilder front = new StringBuilder(), back = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {
				if (i % 2 == 0) {
					front.append(next.charAt(i));
				}
				if ((str.length() % 2 == 0 && i % 2 == 0) || (str.length() % 2 == 1 && i % 2 == 1)) {
					back.append(next.charAt(next.length() - 1 - i));
				}
			}
			next = front.append(back).toString();

		} while (!str.equals(next));

		return list.get(X % list.size());
	}

	public static void main(String[] args) throws Exception {
		X = Integer.parseInt(input.readLine());
		str = input.readLine();

		System.out.println(solution());
	}
}
