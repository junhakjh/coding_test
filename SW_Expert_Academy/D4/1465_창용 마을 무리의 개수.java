import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 28.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AWngfZVa9XwDFAQU&probBoxId=AZGNXHWapmQDFAQP&type=PROBLEM&problemBoxTitle=0826%EC%A3%BC&problemBoxCnt=8
 * @timecomplex
 * @performance 25,744 kb, 147 ms
 * @category #Union-Find
 * @note
 */

class Union {
	int[] parents;

	Union(int n) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	int findSet(int i) {
		if (i == parents[i]) {
			return i;
		}
		return parents[i] = findSet(parents[i]);
	}

	boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}
}

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static Union union;
	static int[][] orders;
	static Set<Integer> set;

	static int solution() {
		for (int[] order : orders) {
			union.union(Math.min(order[0], order[1]), Math.max(order[0], order[1]));
		}

		for (int i = 0; i < n; i++) {
			set.add(union.findSet(i));
		}

		return set.size();
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			union = new Union(n);
			orders = new int[m][2];
			set = new HashSet<>();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(input.readLine());
				orders[i] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, };
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		System.out.print(output);
	}
}
