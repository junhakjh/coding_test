import java.util.*;
import java.io.*;

class Edge {
	int num, cost, prev;

	Edge(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}

	Edge(int num, int cost, int prev) {
		this.num = num;
		this.cost = cost;
		this.prev = prev;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int T, n, m, t, s, g, h;
	static Map<Integer, List<Edge>> map;
	static Set<Integer> candidates;

	static boolean isGH(int a, int b) {
		return (a == g && b == h) || (b == g && a == h);
	}

	static void solution() {
		int[] dists = new int[n + 1];
		boolean[] gh = new boolean[n + 1];
		Arrays.fill(dists, Integer.MAX_VALUE);
		dists[s] = 0;
		Queue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
		for (Edge edge : map.get(s)) {
			pq.offer(new Edge(edge.num, edge.cost, s));
		}

		Set<Integer> answerSet = new HashSet<>();
		while (!pq.isEmpty()) {
			Edge curEdge = pq.poll();
			if (dists[curEdge.num] >= curEdge.cost && (gh[curEdge.prev] || isGH(curEdge.num, curEdge.prev))) {
				gh[curEdge.num] = true;
			}
			if (gh[curEdge.num] && candidates.contains(curEdge.num)) {
				answerSet.add(curEdge.num);
			}
			if (dists[curEdge.num] <= curEdge.cost) {
				continue;
			}
			dists[curEdge.num] = curEdge.cost;

			for (Edge edge : map.get(curEdge.num)) {
				pq.offer(new Edge(edge.num, curEdge.cost + edge.cost, curEdge.num));
			}
		}

		List<Integer> list = new ArrayList<>(answerSet);
		list.sort((a, b) -> Integer.compare(a, b));
		for (int num : list) {
			output.append(num).append(" ");
		}
		output.append("\n");
	}

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(input.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(input.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
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
			candidates = new HashSet<>();
			for (int i = 0; i < t; i++) {
				candidates.add(Integer.parseInt(input.readLine()));
			}

			solution();
		}

		System.out.print(output);
	}
}
