import java.util.*;
import java.io.*;

class Edge {
	int v1;
	int v2;
	int w;

	Edge(int v1, int v2, int w) {
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}
}

class Union {
	int[] parents;

	Union(int n) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = -1;
		}
	}

	int findRoot(int i) {
		if (parents[i] < 0) {
			return i;
		}
		return parents[i] = findRoot(parents[i]);
	}

	boolean union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);

		if (aRoot == bRoot) {
			return false;
		}

		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;

		return true;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static Queue<Edge> pq;

	static int solution() {
		int answer = 0;

		Union union = new Union(N);

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (!union.union(edge.v1, edge.v2)) {
				answer += edge.w;
			}
		}

		int root = union.findRoot(0);
		for (int i = 1; i < N; i++) {
			if (root != union.findRoot(i)) {
				return -1;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.w, e2.w));
		for (int i = 0; i < N; i++) {
			String str = input.readLine();
			for (int j = 0; j < N; j++) {
				int c = str.charAt(j);
				if (c != 48) {
					pq.offer(new Edge(i, j, c >= 97 ? c - 96 : c - 38));
				}
			}
		}

		System.out.println(solution());
	}
}
