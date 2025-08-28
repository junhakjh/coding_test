import java.util.*;
import java.io.*;

class Edge {
	int nodeNum, cost;

	Edge(int nodeNum, int cost) {
		this.nodeNum = nodeNum;
		this.cost = cost;
	}
}

class Node {
	int nodeNum, count;
	long cost;

	Node(int nodeNum, int count, long cost) {
		this.nodeNum = nodeNum;
		this.count = count;
		this.cost = cost;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M, K;
	static Map<Integer, List<Edge>> roadMap;

	static long solution() {
		long answer = Long.MAX_VALUE;

		long[][] distance = new long[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(distance[i], Long.MAX_VALUE);
		}

		Queue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
		pq.offer(new Node(1, 0, 0));
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (distance[node.nodeNum][node.count] < node.cost) {
				continue;
			}
			for (Edge edge : roadMap.get(node.nodeNum)) {
				if (distance[edge.nodeNum][node.count] > node.cost + edge.cost) {
					distance[edge.nodeNum][node.count] = node.cost + edge.cost;
					pq.offer(new Node(edge.nodeNum, node.count, node.cost + edge.cost));
				}
				if (node.count < K) {
					if (distance[edge.nodeNum][node.count + 1] > node.cost) {
						distance[edge.nodeNum][node.count + 1] = node.cost;
						pq.offer(new Node(edge.nodeNum, node.count + 1, node.cost));
					}
				}
			}
		}

		for (int i = 0; i <= K; i++) {
			answer = Math.min(answer, distance[N][i]);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		roadMap = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			roadMap.put(i, new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
					cost = Integer.parseInt(st.nextToken());
			roadMap.get(a).add(new Edge(b, cost));
			roadMap.get(b).add(new Edge(a, cost));
		}

		System.out.println(solution());
	}
}
