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

	long init(int[] arr, int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		return tree[node] = init(arr, node * 2, start, (start + end) / 2)
				+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
	}

	void update(int node, int start, int end, int idx, long plus) {
		if (idx < start || end < idx) {
			return;
		}

		tree[node] += plus;

		if (start != end) {
			update(node * 2, start, (start + end) / 2, idx, plus);
			update(node * 2 + 1, (start + end) / 2 + 1, end, idx, plus);
		}
	}

	long sum(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		return sum(node * 2, start, (start + end) / 2, left, right)
				+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final long DIVIDER = 1000000;

	static int N, Q;
	static SegTree tree;
	static int[] arr;
	static List<int[]> changeQueries;
	static Map<Integer, Map<Long, Long>> sumQueries;
	static List<long[]> queries;

	static void solution() {
		tree.init(arr, 1, 0, N - 1);
		if (sumQueries.get(0) != null) {
			for (long range : sumQueries.get(0).keySet()) {
				sumQueries.get(0).put(range, tree.sum(1, 0, N - 1, (int) (range / DIVIDER), (int) (range % DIVIDER)));
			}
		}
		for (int i = 0; i < changeQueries.size(); i++) {
			int[] query = changeQueries.get(i);
			int idx = query[0] - 1, newNum = query[1];
			int diff = newNum - arr[idx];
			arr[idx] = newNum;
			tree.update(1, 0, N - 1, idx, diff);
			if (sumQueries.get(i + 1) != null) {
				for (long range : sumQueries.get(i + 1).keySet()) {
					sumQueries.get(i + 1).put(range,
							tree.sum(1, 0, N - 1, (int) (range / DIVIDER), (int) (range % DIVIDER)));
				}
			}
		}
		for (long[] query : queries) {
			output.append(sumQueries.get((int) query[0]).get(query[1])).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		tree = new SegTree(N);
		arr = new int[N];
		changeQueries = new ArrayList<>();
		sumQueries = new HashMap<>();
		queries = new ArrayList<>();
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Q = Integer.parseInt(input.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(input.readLine());
			if (st.nextToken().equals("1")) {
				changeQueries.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
			} else {
				int k = Integer.parseInt(st.nextToken());
				long l = Long.parseLong(st.nextToken()) - 1, r = Long.parseLong(st.nextToken()) - 1;
				sumQueries.putIfAbsent(k, new HashMap<>());
				sumQueries.get(k).put(DIVIDER * l + r, (long) 0);
				queries.add(new long[] { k, DIVIDER * l + r });
			}
		}

		solution();

		System.out.print(output);
	}
}
