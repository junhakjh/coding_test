import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] graph;

	static int solution() {
		int answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(r == c) {
						continue;
					}
					if (graph[r][i] != 0 && graph[i][c] != 0) {
						graph[r][c] = Math.min(graph[r][c] == 0 ? Integer.MAX_VALUE : graph[r][c],
								graph[r][i] + graph[i][c]);
						graph[c][r] = Math.min(graph[c][r] == 0 ? Integer.MAX_VALUE : graph[c][r],
								graph[c][i] + graph[i][r]);
					}
				}
			}
		}

		for (int r = 0; r < N; r++) {
			int n = 0;
			for (int c = 0; c < N; c++) {
				n += graph[r][c];
			}
			answer = Math.min(answer, n);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			graph = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
