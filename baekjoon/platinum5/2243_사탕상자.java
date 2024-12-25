import java.util.*;
import java.io.*;

class Node {
	int left;
	int right;
	int value;

	Node() {
		left = 0;
		right = 0;
		value = 0;
	}

	@Override
	public String toString() {
		return "left: " + left + ", right: " + right + ", value: " + value;
	}
}

class SegTree {
	int size;
	Node[] tree;

	SegTree(int size) {
		int h = (int) Math.ceil(Math.log(size) / Math.log(2));
		this.size = (int) Math.pow(2, h + 1);
		tree = new Node[this.size];
	}

	void init(int node, int start, int end) {
		tree[node] = new Node();
		if (start != end) {
			init(node * 2, start, (start + end) / 2);
			init(node * 2 + 1, (start + end) / 2 + 1, end);
		}
	}

	// true이면 사탕이 0으로 변한거
	int update(int node, int start, int end, int idx, int diff) {
		if (idx < start || end < idx) {
			return 0;
		}

		if (start == end) {
			tree[node].value += diff;
			return diff;
		}

		tree[node].left += update(node * 2, start, (start + end) / 2, idx, diff);
		tree[node].right += update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
		tree[node].value = tree[node].left + tree[node].right;
		return diff;
	}

	int getCandy(int node, int start, int end, int rank) {
		if (start == end) {
			tree[node].value--;
			return start + 1;
		}

		int result = 0;
		if (rank <= tree[node].left) {
			result = getCandy(node * 2, start, (start + end) / 2, rank);
			tree[node].left--;
		} else {
			result = getCandy(node * 2 + 1, (start + end) / 2 + 1, end, rank - tree[node].left);
			tree[node].right--;
		}
		tree[node].value--;

		return result;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int CANDY_LEN = 1000000;

	static int N;
	static int[] candies;
	static StringTokenizer[] orders;
	static SegTree tree;

	static void solution() {
		tree = new SegTree(CANDY_LEN);
		tree.init(1, 0, CANDY_LEN - 1);

		for (StringTokenizer order : orders) {
			if (order.nextToken().equals("1")) {
				output.append(tree.getCandy(1, 0, CANDY_LEN - 1, Integer.parseInt(order.nextToken()))).append("\n");
			} else {
				int idx = Integer.parseInt(order.nextToken()) - 1, diff = Integer.parseInt(order.nextToken());
				candies[idx] += diff;
				tree.update(1, 0, CANDY_LEN - 1, idx, diff);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		candies = new int[CANDY_LEN];
		orders = new StringTokenizer[N];
		for (int i = 0; i < N; i++) {
			orders[i] = new StringTokenizer(input.readLine());
		}

		solution();

		System.out.print(output);
	}
}
