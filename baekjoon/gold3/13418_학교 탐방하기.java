import java.util.*;
import java.io.*;

class Edge {
	int e1;
	int e2;
	int weight;

	Edge(int e1, int e2, int weight) {
		this.e1 = e1;
		this.e2 = e2;
		this.weight = weight;
	}
}

class Union {
	int[] parents;

	Union(int size) {
		parents = new int[size + 1];
		for (int i = 0; i <= size; i++) {
			parents[i] = -1;
		}
	}

	int findRoot(int node) {
		if (parents[node] < 0) {
			return node;
		}
		return parents[node] = findRoot(parents[node]);
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
	static StringTokenizer st;

	static int n, m;
	static Queue<Edge> maxPq;
	static Queue<Edge> minPq;
	static Union maxUnion;
	static Union minUnion;

	static long solution() {
		long answer = 0;

		int max = 0, min = 0;
		while (!maxPq.isEmpty() && !minPq.isEmpty()) {
			Edge edgeA = maxPq.poll();
			if (maxUnion.union(edgeA.e1, edgeA.e2)) {
				max += edgeA.weight;
			}
			Edge edgeB = minPq.poll();
			if (minUnion.union(edgeB.e1, edgeB.e2)) {
				min += edgeB.weight;
			}
		}

		answer = (int) Math.abs(Math.pow(n - max, 2) - Math.pow(n - min, 2));

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maxPq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		minPq = new PriorityQueue<>((o1, o2) -> (-1) * Integer.compare(o1.weight, o2.weight));
		maxUnion = new Union(n);
		minUnion = new Union(n);
		for (int i = 0; i <= m; i++) {
			st = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			maxPq.offer(new Edge(a, b, w));
			minPq.offer(new Edge(a, b, w));
		}

		System.out.println(solution());
	}
}
