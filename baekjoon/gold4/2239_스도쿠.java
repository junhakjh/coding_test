import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int[][] map;
	static List<Integer> holes;
	static boolean isAnswer;

	static Set<Integer> findCand(int tr, int tc) {
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i <= 9; i++) {
			set.add(i);
		}
		// 가로
		for (int c = 0; c < 9; c++) {
			if (map[tr][c] > 0) {
				set.remove(map[tr][c]);
			}
		}
		// 세로
		for (int r = 0; r < 9; r++) {
			if (map[r][tc] > 0) {
				set.remove(map[r][tc]);
			}
		}
		// 정사각형
		int sr = (tr / 3) * 3, sc = (tc / 3) * 3;
		for (int r = sr; r < sr + 3; r++) {
			for (int c = sc; c < sc + 3; c++) {
				if (map[r][c] > 0) {
					set.remove(map[r][c]);
				}
			}
		}

		return set;
	}

	static void dfs(int n) {
		if (n == holes.size()) {
			isAnswer = true;
			return;
		}
		int r = holes.get(n) / 9, c = holes.get(n) % 9;
		if (map[r][c] == 0) {
			Set<Integer> cand = findCand(r, c);
			for (int num : cand) {
				map[r][c] = num;
				dfs(n + 1);
				if (isAnswer) {
					return;
				}
				map[r][c] = 0;
			}
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		map = new int[9][9];
		holes = new ArrayList<>();
		isAnswer = false;
		for (int r = 0; r < 9; r++) {
			String row = input.readLine();
			for (int c = 0; c < 9; c++) {
				map[r][c] = row.charAt(c) - 48;
				if (map[r][c] == 0) {
					holes.add(9 * r + c);
				}
			}
		}

		dfs(0);

		for (int[] row : map) {
			for (int item : row) {
				output.append(item);
			}
			output.append("\n");
		}

		System.out.println(output);
	}
}
