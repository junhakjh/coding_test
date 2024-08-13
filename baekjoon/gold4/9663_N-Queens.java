import java.util.*;
import java.io.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int answer = 0;
	static int n;
	static ArrayDeque<int[]> queens = new ArrayDeque<>();
	static int[][] map;

	static void dfs(int col) {
		for (int row = 0; row < n; row++) {
			if (map[row][col] == 0) {
				if (col == n - 1) {
					answer++;
					continue;
				}

				map[row][col]++;
				for (int i = 1; i < n - col; i++) {
					map[row][col + i]++;
					if (row - i >= 0) {
						map[row - i][col + i]++;
					}
					if (row + i < n) {
						map[row + i][col + i]++;
					}
				}
				dfs(col + 1);
				map[row][col]--;
				for (int i = 1; i < n - col; i++) {
					map[row][col + i]--;
					if (row - i >= 0) {
						map[row - i][col + i]--;
					}
					if (row + i < n) {
						map[row + i][col + i]--;
					}
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(input.readLine());
		map = new int[n][n];

		dfs(0);

		System.out.println(answer);
	}
}
