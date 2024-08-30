import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 30.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV15StKqAQkCFAYD&probBoxId=AZGNXHWapmQDFAQP&type=PROBLEM&problemBoxTitle=0826%EC%A3%BC&problemBoxCnt=12
 * @timecomplex
 * @performance 81,320 kb, 844 ms
 * @category #mst #크루스칼 알고리즘
 * @note
 */

class Edge implements Comparable<Edge> {
	int e1;
	int e2;
	double weight;

	public Edge(int e1, int e2, double weight) {
		this.e1 = e1;
		this.e2 = e2;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		return Double.compare(this.weight, e.weight);
	}
}

class Union {
	int[] parents;

	public Union(int n) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = -1;
		}
	}

	public int findRoot(int i) {
		if (parents[i] < 0) {
			return i;
		}
		return parents[i] = findRoot(parents[i]);
	}

	public boolean union(int a, int b) {
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

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[][] coors;
	static Queue<Edge> heap;
	static double e;

	static long solution() {
		long answer = 0;

		Union union = new Union(n);

		while (!heap.isEmpty()) {
			Edge edge = heap.poll();
			if (union.union(edge.e1, edge.e2)) {
				answer += edge.weight;
			}
		}

		return Math.round(answer * e);
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(input.readLine());
			heap = new PriorityQueue<>();
			coors = new int[n][2];
			StringTokenizer stR = new StringTokenizer(input.readLine());
			StringTokenizer stC = new StringTokenizer(input.readLine());
			int r = Integer.parseInt(stR.nextToken()), c = Integer.parseInt(stC.nextToken());
			coors[0] = new int[] { r, c };
			for (int i = 1; i < n; i++) {
				r = Integer.parseInt(stR.nextToken());
				c = Integer.parseInt(stC.nextToken());
				coors[i] = new int[] { r, c };
				for (int j = 0; j < i; j++) {
					heap.offer(new Edge(i, j, (long) Math.pow(coors[j][0] - r, 2) + Math.pow(coors[j][1] - c, 2)));
				}
			}
			e = Double.parseDouble(input.readLine());

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
