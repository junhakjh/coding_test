import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 29.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV15B1cKAKwCFAYD&probBoxId=AZGNXHWapmQDFAQP&type=PROBLEM&problemBoxTitle=0826%EC%A3%BC&problemBoxCnt=8
 * @timecomplex
 * @performance 289,400 kb, 3,673 ms
 * @category #mst #프림 알고리즘
 * @note
 */

class Node implements Comparable<Node> {
	int to;
	int weight;

	public Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node node) {
		return Integer.compare(weight, node.weight);
	}
}

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int v, e;
	static Map<Integer, List<Node>> nodes;
	static boolean[] visited;

	static long solution() {
		long answer = 0;

		Queue<Node> heap = new PriorityQueue<Node>();
		heap.offer(new Node(1, 0));
		while (!heap.isEmpty()) {
			Node node = heap.poll();
			if (!visited[node.to]) {
				visited[node.to] = true;
				answer += node.weight;
				for (Node next : nodes.get(node.to)) {
					if (!visited[next.to]) {
						heap.offer(next);
					}
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			nodes = new HashMap<>();
			for (int i = 1; i <= v; i++) {
				nodes.put(i, new ArrayList<>());
			}
			visited = new boolean[v + 1];
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(input.readLine());
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				nodes.get(node1).add(new Node(node2, weight));
				nodes.get(node2).add(new Node(node1, weight));
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
