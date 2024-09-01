import java.util.*;
import java.io.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int v, e;
	static Map<Integer, List<int[]>> edges;

	static long solution() {
		long answer = 0;

		int num = 0;
		boolean[] visited = new boolean[v + 1];
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		for (int[] edge : edges.get(1)) {
			pq.offer(edge);
		}
		visited[1] = true;

		while (++num < v) {
			int[] edge = pq.poll();
			while (visited[edge[0]]) {
				edge = pq.poll();
			}
			visited[edge[0]] = true;
			answer += edge[1];
			for (int[] nEdge : edges.get(edge[0])) {
				if (!visited[nEdge[0]]) {
					pq.offer(nEdge);
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		edges = new HashMap<>();
		for (int i = 1; i <= v; i++) {
			edges.put(i, new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.get(a).add(new int[] { b, w });
			edges.get(b).add(new int[] { a, w });
		}

		System.out.println(solution());
	}
}
