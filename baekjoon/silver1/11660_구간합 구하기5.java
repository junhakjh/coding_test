import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 14.
 * @link https://www.acmicpc.net/problem/11660
 * @timecomplex O(n)
 * @performance 117,556 kb, 900 ms
 * @category #dp
 * @note
 */

class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	static int n, m;
	static int[][] graph, orders;

	static void solution() {
		for (int i = 0; i < m; i++) {
			int r1 = orders[i][0], c1 = orders[i][1], r2 = orders[i][2], c2 = orders[i][3];
			int sum = 0;
			for (int r = r1; r <= r2; r++) {
				sum += graph[r][c2] - graph[r][c1 - 1];
			}
			output.append(sum).append("\n");
		}
	}

	public static void main(String args[]) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n + 2][n + 2];
		orders = new int[m][4];
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(input.readLine());
			int sum = 0;
			for (int c = 1; c <= n; c++) {
				sum += Integer.parseInt(st.nextToken());
				graph[r][c] = sum;
			}
		}
		for (int r = 0; r < m; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < 4; c++) {
				orders[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		solution();

		System.out.print(output);
	}
}
