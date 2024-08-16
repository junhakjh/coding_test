import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 16.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AWErcQmKy6kDFAXi&solveclubId=AZCWNHD6B-8DFASB&problemBoxTitle=0812%EC%A3%BC&problemBoxCnt=7&probBoxId=AZFIwxLqKmIDFAQW
 * @timecomplex O(n^2)
 * @performance 47,664 kb, 190 ms
 * @category #완전탐색
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[][] ingredients;
	static Map<Integer, List<Integer>> noTogether;
	static int[] visited;
	static int answer;

	static void dfs(int curI, boolean flag) {
		// 현재 놈을 데려온 경우, 상성인 놈들 표시
		if (flag) {
			for (int j : noTogether.get(curI)) {
				visited[j]++;
			}
		}
		boolean check = false;
		for (int i = curI + 1; i < n; i++) {
			if (visited[i] == 0) {
				dfs(i, true); // 데려가기
				dfs(i, false); // 안데려가기
				check = true;
				break;
			}
		}
		if (flag) {
			for (int j : noTogether.get(curI)) {
				visited[j]--;
			}
		}
		if (!check) {
			answer++;
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 1;
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			ingredients = new int[m][2];
			noTogether = new HashMap<>();
			visited = new int[n];
			for (int key = 0; key < n; key++) {
				noTogether.put(key, new ArrayList<>());
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
				int key = Math.min(a, b), value = Math.max(a, b);
				noTogether.get(key).add(value);
				ingredients[i][0] = a;
				ingredients[i][1] = b;
			}
			for (int i = 0; i < n; i++) {
				dfs(i, true);
			}
			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(output);
	}
}
