import java.util.*;
import java.io.*;

/**
 * 
 * @author 은서파
 * @since 2024. 8. 13.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AWgv9va6HnkDFAW0&probBoxId=AZFIwxLqKmIDFAQW&type=PROBLEM&problemBoxTitle=0812%EC%A3%BC&problemBoxCnt=3
 * @timecomplex O(n!)
 * @performance 20,404 kb, 2,427 ms
 * @category #백트래킹
 * @note
 */

class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	static int wins, loses;
	static int[] cards;
	static int[] counterCards;

	static void permutation(int[] counters, int depth, boolean[] visited) {
		if (depth == 9) {
			int me = 0, counter = 0;
			for (int i = 0; i < 9; i++) {
				if (cards[i] > counters[i]) {
					me += cards[i] + counters[i];
				} else {
					counter += cards[i] + counters[i];
				}
			}
			if (me > counter)
				wins++;
			else if (me < counter)
				loses++;
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				counters[depth] = counterCards[i];
				permutation(counters, depth + 1, visited);
				visited[i] = false;
			}
		}
	}

	static void solution() {

		boolean[] nums = new boolean[18];
		counterCards = new int[9];
		for (int i = 0; i < 9; i++) {
			nums[cards[i] - 1] = true;
		}
		int j = 0;
		for (int i = 0; i < 18; i++) {
			if (!nums[i]) {
				counterCards[j++] = i + 1;
			}
		}

		permutation(new int[9], 0, new boolean[9]);
	}

	public static void main(String args[]) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			wins = 0;
			loses = 0;
			st = new StringTokenizer(input.readLine());
			cards = new int[9];
			for (int i = 0; i < 9; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}

			solution();

			output.append("#").append(tc).append(" ").append(wins).append(" ").append(loses).append("\n");
		}

		System.out.println(output);
	}
}
