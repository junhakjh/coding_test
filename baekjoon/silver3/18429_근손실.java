import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 13.
 * @link https://www.acmicpc.net/problem/18429
 * @timecomplex O(n!)
 * @performance 14184 kb, 132 ms
 * @category #백트래킹
 * @note
 */

class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, k;
	static int answer = 0;
	static int[] strength;

	static void permutation(int depth, boolean[] visited, int curPower) {
		if (depth == n) {
			answer++;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if (curPower - k + strength[i] >= 500) {
					permutation(depth + 1, visited, curPower - k + strength[i]);
				}
				visited[i] = false;
			}
		}
	}

	public static void main(String args[]) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(input.readLine());
		strength = new int[n];
		for (int i = 0; i < n; i++) {
			strength[i] = Integer.parseInt(st.nextToken());
		}

		permutation(0, new boolean[n], 500);

		System.out.println(answer);
	}
}
