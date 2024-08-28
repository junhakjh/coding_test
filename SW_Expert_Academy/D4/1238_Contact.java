import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 28.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV15B1cKAKwCFAYD&probBoxId=AZGNXHWapmQDFAQP&type=PROBLEM&problemBoxTitle=0826%EC%A3%BC&problemBoxCnt=8
 * @timecomplex
 * @performance 20,004 kb, 124 ms
 * @category #bfs
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int start;
	static Map<Integer, List<Integer>> contacts;
	static boolean[] visited;

	static int solution() {
		int answer = start;
		int maxDepth = 0;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { start, 0 });
		visited[start] = true;

		while (!q.isEmpty()) {
			int[] nums = q.poll();
			int num = nums[0], depth = nums[1];
			if (depth > maxDepth) {
				maxDepth = depth;
				answer = num;
			} else if (depth == maxDepth) {
				answer = Math.max(answer, num);
			}
			for (int nNum : contacts.get(num)) {
				if (!visited[nNum]) {
					visited[nNum] = true;
					q.offer(new int[] { nNum, depth + 1 });
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		for (int tc = 1; tc <= 10; tc++) {
			contacts = new HashMap<>();
			for (int i = 1; i <= 100; i++) {
				contacts.put(i, new ArrayList<>());
			}
			st = new StringTokenizer(input.readLine());
			int n = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			visited = new boolean[101];
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < n / 2; i++) {
				int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
				contacts.get(a).add(b);
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
