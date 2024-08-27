import java.io.*;
import java.util.*;

class SegmentTree {
	int treeSize;
	int[] tree;

	SegmentTree(int arrSize) {
		int height = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
		treeSize = (int) Math.pow(2, height + 1);
		tree = new int[treeSize];
	}

	int init(int node, int start, int end, int[] arr) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		return tree[node] = Math.max(init(2 * node, start, (start + end) / 2, arr),
				init(2 * node + 1, (start + end) / 2 + 1, end, arr));
	}

	int getMax(int node, int start, int end, int left, int right) {
		if (end < left || start > right) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}

		return Math.max(getMax(node * 2, start, (start + end) / 2, left, right),
				getMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}
}

public class Main {

	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[] arr;

	static void solution() {
		SegmentTree tree = new SegmentTree(n);
		tree.init(1, 0, n - 1, arr);
		
		for(int i = m - 1; i < n - m + 1; i++) {
			int l = i - (m - 1), r = i + (m - 1);
			output.append(tree.getMax(1, 0, n - 1, l, r)).append(" ");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		solution();

		System.out.println(output);
	}

}
