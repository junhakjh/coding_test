import java.util.*;
import java.io.*;

class Edge {
	int num, cost;

	Edge(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static Map<Integer, List<Edge>> map;
	static Set<Integer> houses;
	static List<Integer> stores;

	static int solution() {
		int answer = -1;

		boolean[] visited = new boolean[n + 1];
		Queue<Edge> pq = new PriorityQueue<>((a, b) -> {
			if (a.cost == b.cost) {
				return Integer.compare(a.num, b.num);
			}
			return Integer.compare(a.cost, b.cost);
		});
		for (int store : stores) {
			visited[store] = true;
			for (Edge edge : map.get(store)) {
				pq.offer(edge);
			}
		}
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (houses.contains(edge.num)) {
				answer = edge.num;
				break;
			}
			if (visited[edge.num]) {
				continue;
			}
			visited[edge.num] = true;
			for (Edge nextEdge : map.get(edge.num)) {
				pq.offer(new Edge(nextEdge.num, edge.cost + nextEdge.cost));
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
					cost = Integer.parseInt(st.nextToken());
			map.get(a).add(new Edge(b, cost));
			map.get(b).add(new Edge(a, cost));
		}
		st = new StringTokenizer(input.readLine());
		int houseLength = Integer.parseInt(st.nextToken());
		int storeLength = Integer.parseInt(st.nextToken());
		houses = new HashSet<>();
		stores = new ArrayList<>();
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < houseLength; i++) {
			houses.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < storeLength; i++) {
			stores.add(Integer.parseInt(st.nextToken()));
		}

		System.out.println(solution());
	}
}
