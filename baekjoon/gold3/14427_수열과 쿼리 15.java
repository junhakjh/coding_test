import java.util.*;
import java.io.*;

class SegTree {
	int[] tree;
	int size;

	SegTree(int size) {
		int h = (int) Math.ceil(Math.log(size) / Math.log(2));
		this.size = (int) Math.pow(2, h + 1);
		tree = new int[this.size];
	}

	int init(int[] arr, int node, int start, int end) {
		if (start == end) {
			return tree[node] = start;
		}

		int leftI = init(arr, node * 2, start, (start + end) / 2);
		int rightI = init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
		return tree[node] = arr[leftI] <= arr[rightI] ? leftI : rightI;
	}

	int update(int[] arr, int node, int start, int end, int index) {
		if (index < start || end < index || start == end) {
			return tree[node];
		}

		int leftI = update(arr, node * 2, start, (start + end) / 2, index);
		int rightI = update(arr, node * 2 + 1, (start + end) / 2 + 1, end, index);
		return tree[node] = arr[leftI] <= arr[rightI] ? leftI : rightI;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static int[] A;
	static StringTokenizer[] queries;
	static SegTree tree;

	static void solution() {
		tree = new SegTree(N);
		tree.init(A, 1, 0, N - 1);
		for (StringTokenizer query : queries) {
			int order = Integer.parseInt(query.nextToken());
			if (order == 1) {
				int index = Integer.parseInt(query.nextToken()) - 1, num = Integer.parseInt(query.nextToken());
				A[index] = num;
				tree.update(A, 1, 0, N - 1, index);
			} else {
				output.append(tree.tree[1] + 1).append("\n");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		A = new int[N];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(input.readLine());
		queries = new StringTokenizer[M];
		for (int i = 0; i < M; i++) {
			queries[i] = new StringTokenizer(input.readLine());
		}

		solution();

		System.out.println(output);
	}
}
