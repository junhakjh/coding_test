import java.util.*;
import java.io.*;

class Tree {
	int age;
	Tree prev;
	Tree next;

	Tree(int value, Tree prev, Tree next) {
		this.age = value;
		this.prev = prev;
		this.next = next;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 }, dc = { 1, 1, 0, -1, -1, -1, 0, 1 };

	static int N, M, K;
	static int[][] map, A;
	static Map<Integer, Tree> trees;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}
		return true;
	}

	static Map<Integer, Tree> spring() {
		Map<Integer, Tree> deadTreeHeads = new HashMap<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int id = r * N + c;
				Tree tree = trees.get(id);
				while (tree != null) {
					if (tree.age <= map[r][c]) {
						map[r][c] -= tree.age;
						tree.age++;
					} else {
						if (tree.prev == null) {
							trees.put(id, null);
						} else {
							tree.prev.next = null;
						}
						deadTreeHeads.put(id, tree);
						break;
					}
					tree = tree.next;
				}
			}
		}
		return deadTreeHeads;
	}

	static void summer(Map<Integer, Tree> deadTreeHeads) {
		for (int id : deadTreeHeads.keySet()) {
			int r = id / N, c = id % N;
			Tree tree = deadTreeHeads.get(id);
			while (tree != null) {
				map[r][c] += tree.age / 2;
				tree = tree.next;
			}
		}
	}

	static void fall() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int id = r * N + c;
				Tree tree = trees.get(id);
				while (tree != null) {
					if (tree.age % 5 == 0) {
						for (int i = 0; i < 8; i++) {
							int nr = r + dr[i], nc = c + dc[i];
							if (isIn(nr, nc)) {
								Tree head = trees.get(nr * N + nc);
								Tree newHead = new Tree(1, null, head);
								if (head != null) {
									head.prev = newHead;
								}
								trees.put(nr * N + nc, newHead);
							}
						}
					}
					tree = tree.next;
				}
			}
		}
	}

	static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] += A[r][c];
			}
		}
	}

	static int solution() {
		for (int y = 0; y < K; y++) {
			summer(spring());
			fall();
			winter();
		}

		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				Tree tree = trees.get(r * N + c);
				while (tree != null) {
					result++;
					tree = tree.next;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		A = new int[N][N];
		trees = new HashMap<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = 5;
				trees.put(r * N + c, null);
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken()) - 1,
					age = Integer.parseInt(st.nextToken());
			trees.put(r * N + c, new Tree(age, null, null));
		}

		System.out.println(solution());
	}
}
