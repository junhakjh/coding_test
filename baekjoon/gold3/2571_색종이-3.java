import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static boolean[][] map;

	static int solution() {
		int answer = 0;

		for (int i = 0; i < 10000; i++) {
			int sr = i / 100, sc = i % 100;
			if (!map[sr][sc]) {
				continue;
			}
			int maxC = 99;
			for (int r = sr; r <= 99; r++) {
				if (!map[r][sc]) {
					break;
				}
				for (int c = sc; c <= maxC; c++) {
					if (!map[r][c]) {
						maxC = c - 1;
						break;
					}
					answer = Math.max(answer, (r - sr + 1) * (c - sc + 1));
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		map = new boolean[100][100];
		int n = Integer.parseInt(input.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(input.readLine());
			int sc = Integer.parseInt(st.nextToken()) - 1, sr = Integer.parseInt(st.nextToken()) - 1;
			for (int c = sc; c < sc + 10; c++) {
				for (int r = sr; r < sr + 10; r++) {
					map[r][c] = true;
				}
			}
		}

		System.out.println(solution());
	}
}
