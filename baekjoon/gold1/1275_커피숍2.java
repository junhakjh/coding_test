import java.util.*;
import java.io.*;

class SegTree {
	long[] tree;
	int size;

	SegTree(long size) {
		int h = (int) Math.ceil((Math.log(size) / Math.log(2)));
		this.size = (int) Math.pow(2, h + 1);
		tree = new long[this.size];
	}

	long init(long[] arr, int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		return tree[node] = init(arr, node * 2, start, (start + end) / 2)
				+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
	}

	void update(long[] arr, int node, int start, int end, int idx, long plus) {
		if (idx < start || end < idx) {
			return;
		}

		tree[node] += plus;

		if (start != end) {
			update(arr, node * 2, start, (start + end) / 2, idx, plus);
			update(arr, node * 2 + 1, (start + end) / 2 + 1, end, idx, plus);
		}
	}

	long sum(long[] arr, int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		return sum(arr, node * 2, start, (start + end) / 2, left, right)
				+ sum(arr, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, Q;
	static SegTree tree;
	static long[] arr;
	static int[][] queries;

	static void solution() {
		tree.init(arr, 1, 0, N - 1);
		for (int[] query : queries) {
			output.append(
					tree.sum(arr, 1, 0, N - 1, Math.min(query[0], query[1]) - 1, Math.max(query[0], query[1]) - 1))
					.append("\n");
			long diff = query[3] - arr[query[2] - 1];
			arr[query[2] - 1] = query[3];
			tree.update(arr, 1, 0, N - 1, query[2] - 1, diff);
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		tree = new SegTree(N);
		arr = new long[N];
		queries = new int[Q][4];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < 4; j++) {
				queries[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solution();

		System.out.println(output);
	}
}
