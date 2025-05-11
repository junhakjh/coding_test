import java.util.*;
import java.io.*;

class Edge {
	int start;
	int target;
	int cost;

	Edge(int start, int target, int cost) {
		this.start = start;
		this.target = target;
		this.cost = cost;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int start, target;
	static Map<Integer, List<Edge>> edgeMap = new HashMap<>();
	static Map<Integer, Integer> costs = new HashMap<>();
	static Map<Integer, Integer> history = new HashMap<>();

	static void solution() {
		Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost > e2.cost ? 1 : -1);
		for (Edge edge : edgeMap.get(start)) {
			pq.offer(edge);
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int curCost = costs.get(edge.start) + edge.cost;
			if (curCost < costs.get(edge.target)) {
				costs.put(edge.target, curCost);
				history.put(edge.target, edge.start);
				if (edge.target != target && edgeMap.containsKey(edge.target)) {
					for (Edge nextEdge : edgeMap.get(edge.target)) {
						pq.offer(nextEdge);
					}
				}
			}
		}

		int node = target, num = 1;
		List<Integer> reversed = new ArrayList<>();
		reversed.add(node);
		while (node != start) {
			num++;
			node = history.get(node);
			reversed.add(node);
		}
		output.append(costs.get(target)).append("\n").append(num).append("\n");
		for (int i = reversed.size() - 1; i >= 0; i--) {
			output.append(reversed.get(i));
			if (i != 0) {
				output.append(" ");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		M = Integer.parseInt(input.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
					cost = Integer.parseInt(st.nextToken());
			edgeMap.putIfAbsent(a, new ArrayList<>());
			edgeMap.get(a).add(new Edge(a, b, cost));
			costs.put(a, Integer.MAX_VALUE);
			costs.put(b, Integer.MAX_VALUE);
			history.put(a, a);
			history.put(b, b);
		}
		st = new StringTokenizer(input.readLine());
		start = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		costs.put(start, 0);

		solution();

		System.out.println(output);
	}
}
