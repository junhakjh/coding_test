import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 28.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AWBJKA6qr2oDFAWr&probBoxId=AZGNXHWapmQDFAQP&type=PROBLEM&problemBoxTitle=0826%EC%A3%BC&problemBoxCnt=8
 * @timecomplex
 * @performance 109,268 kb, 672 ms
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

	static String solution() {
		StringBuilder answer = new StringBuilder();

		for (int[] order : orders) {
			if (order[0] == 0) {
				union.union(Math.min(order[1], order[2]), Math.max(order[1], order[2]));
			} else {
				if (union.findSet(order[1]) == union.findSet(order[2])) {
					answer.append(1);
				} else {
					answer.append(0);
				}
			}
		}

		return answer.toString();
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			union = new Union(n);
			orders = new int[m][3];
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(input.readLine());
				orders[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1,
						Integer.parseInt(st.nextToken()) - 1 };
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		System.out.print(output);
	}

}
