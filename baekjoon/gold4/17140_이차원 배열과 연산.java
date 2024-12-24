import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int R, C, K;
	static int[][] matrix;

	static int doR() {
		int cLen = 0;
		for (int r = 0; r < 100; r++) {
			int[] arr = new int[101];
			for (int c = 0; c < 100; c++) {
				arr[matrix[r][c]] += 1;
			}
			Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				} else {
					return Integer.compare(o1[1], o2[1]);
				}
			});
			for (int i = 1; i <= 100; i++) {
				if (arr[i] == 0) {
					continue;
				}
				pq.offer(new int[] { i, arr[i] });
			}
			int nc = 0;
			while (!pq.isEmpty() && nc < 100) {
				int[] item = pq.poll();
				matrix[r][nc] = item[0];
				matrix[r][nc + 1] = item[1];
				nc += 2;
			}
			cLen = Math.max(cLen, nc);
			while (nc < 100) {
				matrix[r][nc++] = 0;
			}
		}

		return cLen;
	}

	static int doC() {
		int rLen = 0;
		for (int c = 0; c < 100; c++) {
			int[] arr = new int[101];
			for (int r = 0; r < 100; r++) {
				arr[matrix[r][c]] += 1;
			}
			Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				} else {
					return Integer.compare(o1[1], o2[1]);
				}
			});
			for (int i = 1; i <= 100; i++) {
				if (arr[i] == 0) {
					continue;
				}
				pq.offer(new int[] { i, arr[i] });
			}
			int nr = 0;
			while (!pq.isEmpty() && nr < 100) {
				int[] item = pq.poll();
				matrix[nr][c] = item[0];
				matrix[nr + 1][c] = item[1];
				nr += 2;
			}
			rLen = Math.max(rLen, nr);
			while (nr < 100) {
				matrix[nr++][c] = 0;
			}
		}

		return rLen;
	}

	static int solution() {
		int turn = 0;

		int rLen = 3, cLen = 3;
		while (turn <= 100) {
			if (matrix[R][C] == K) {
				break;
			}
			if (rLen >= cLen) {
				cLen = doR();
			} else {
				rLen = doC();
			}
			turn++;
		}

		return turn == 101 ? -1 : turn;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());
		matrix = new int[100][100];
		for (int r = 0; r < 3; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < 3; c++) {
				matrix[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.print(solution());
	}
}
