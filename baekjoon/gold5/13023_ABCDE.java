import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 28.
 * @link https://www.acmicpc.net/problem/13023
 * @timecomplex
 * @performance 201,320 kb, 360 ms
 * @category #dfs
 * @note
 */

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static Map<Integer, List<Integer>> friends;
	static boolean[] visited;
	static int answer = 0;

	static void dfs(int i, int depth) {
		if (depth == 5) {
			answer = 1;
			return;
		}
		for (int friend : friends.get(i)) {
			if (!visited[friend]) {
				visited[friend] = true;
				dfs(friend, depth + 1);
				if (answer == 1) {
					return;
				}
				visited[friend] = false;
			}
		}
	}

	static int solution() {
		for (int i = 0; i < n; i++) {
			visited[i] = true;
			dfs(i, 1);
			if (answer == 1) {
				break;
			}
			visited[i] = false;
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		friends = new HashMap<>();
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			friends.put(i, new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friends.get(a).add(b);
			friends.get(b).add(a);
		}

		System.out.println(solution());
	}

}
