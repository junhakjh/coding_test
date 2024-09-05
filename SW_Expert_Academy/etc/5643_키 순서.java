import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 5.
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo
 * @timecomplex
 * @performance 101,452 kb, 2,385 ms
 * @category #bfs
 * @note
 */

class Node {
	List<Integer> children = new ArrayList<>();
	List<Integer> parents = new ArrayList<>();

	void addChild(int child) {
		children.add(child);
	}

	void addParents(int parent) {
		parents.add(parent);
	}
}

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int solution(int n, Node[] nodes) {
		int answer = 0;

		boolean[] visited ;
		for (int i = 1; i <= n; i++) {
			int num = 0;
			Queue<Integer> parentsQ = new ArrayDeque<>();
			parentsQ.offer(i);
			visited = new boolean[n + 1];
			visited[i] = true;
			while (!parentsQ.isEmpty()) {
				int node = parentsQ.poll();
				num++;
				for (int parent : nodes[node].parents) {
					if(!visited[parent]) {
						parentsQ.offer(parent);
						visited[parent] = true;
					}
				}
			}
			Queue<Integer> childrenQ = new ArrayDeque<>();
			childrenQ.offer(i);
			visited = new boolean[n + 1];
			visited[i] = true;
			while (!childrenQ.isEmpty()) {
				int node = childrenQ.poll();
				num++;
				for (int child : nodes[node].children) {
					if(!visited[child]) {
						childrenQ.offer(child);
						visited[child] = true;
					}
				}
			}
			if(num - 1 == n) {
				answer++;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(input.readLine());
			int m = Integer.parseInt(input.readLine());
			Node[] nodes = new Node[n + 1];
			for(int i = 1; i <= n; i++) {
				nodes[i] = new Node();
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				nodes[a].addChild(b);
				nodes[b].addParents(a);
			}

			output.append("#").append(tc).append(" ").append(solution(n, nodes)).append("\n");
		}

		System.out.print(output);
	}
}
