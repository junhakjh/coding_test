import java.util.*;
import java.io.*;

class SegmentTree {
	int treeSize;
	long[] tree;

	SegmentTree(int arrSize) {
		int height = (int) Math.ceil((Math.log(arrSize) / Math.log(2)));
		treeSize = (int) (Math.pow(2, height + 1));
		tree = new long[treeSize];
	}

	long init(long[] arr, int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		return tree[node] = init(arr, node * 2, start, (start + end) / 2)
				+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
	}

	void update(int node, int start, int end, long idx, long diff) {
		if (idx < start || end < idx) {
			return;
		}

		tree[node] += diff;

		if (start != end) {
			update(node * 2, start, (start + end) / 2, idx, diff);
			update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
		}
	}

	long sum(int node, int start, int end, long left, long right) {
		if (left > end || right < start) {
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

	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m, k;
	static long[] arr;
	static long[][] commands;

	static void solution() {
		SegmentTree tree = new SegmentTree(n);

		tree.init(arr, 1, 1, n);

		for (long[] command : commands) {
			if (command[0] == 1) {
				tree.update(1, 1, n, command[1], command[2] - arr[(int) command[1]]);
				arr[(int) command[1]] = command[2];
			} else {
				System.out.println(tree.sum(1, 1, n, command[1], command[2]));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new long[n + 1];
		commands = new long[m + k][3];

		for (int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(input.readLine());
		}
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(input.readLine());
			commands[i][0] = Long.parseLong(st.nextToken());
			commands[i][1] = Long.parseLong(st.nextToken());
			commands[i][2] = Long.parseLong(st.nextToken());
		}

		solution();

		System.out.print(output);
	}
}
