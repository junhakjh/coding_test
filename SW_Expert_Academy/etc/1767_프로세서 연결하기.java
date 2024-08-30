import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 30.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV4suNtaXFEDFAUf&probBoxId=AZGNXHWapmQDFAQP&type=PROBLEM&problemBoxTitle=0826%EC%A3%BC&problemBoxCnt=12
 * @timecomplex
 * @performance 59,360 kb, 294 ms
 * @category #완전탐색
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };

	static int n;
	static int[][] map;
	static List<Integer> cores;
	static List<Integer> realCores;
	static int[] permutations;

	static int answer;
	static int maxCores;
	static Queue<int[]> heap;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n) {
			return false;
		}
		return true;
	}

	static void permutation(int depth, int coreNum, int sum) {
		if (depth == cores.size()) {
			heap.offer(new int[] { coreNum, sum });
			maxCores = Math.max(maxCores, coreNum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int r = cores.get(depth) / n + dr[i], c = cores.get(depth) % n + dc[i];
			boolean flag = false;
			int plus = 0;
			List<int[]> addition = new ArrayList<>();
			while (isIn(r, c)) {
				if (map[r][c] != 0) {
					flag = true;
					break;
				}
				plus++;
				map[r][c] = 2;
				addition.add(new int[] { r, c });
				r += dr[i];
				c += dc[i];
			}
			if (!flag) {
				permutation(depth + 1, coreNum + 1, sum + plus);
				for (int[] coor : addition) {
					map[coor[0]][coor[1]] = 0;
				}
			} else {
				for (int[] coor : addition) {
					map[coor[0]][coor[1]] = 0;
				}
				if(coreNum + (cores.size() - (depth + 1)) < maxCores) {
					continue;
				}
				permutation(depth + 1, coreNum, sum);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(input.readLine());
			map = new int[n][n];
			cores = new ArrayList<>();
			realCores = new ArrayList<>();
			maxCores = 0;
			heap = new PriorityQueue<>((a, b) -> {
				if (a[0] == b[0]) {
					return Integer.compare(a[1], b[1]);
				} else {
					return (-1) * Integer.compare(a[0], b[0]);
				}
			});
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) {
						if (r != 0 && r != n - 1 && c != 0 && c != n - 1) {
							cores.add(r * n + c);
						}
						realCores.add(r * n + c);
					}
				}
			}
			permutations = new int[cores.size()];

			permutation(0, 0, 0);

			output.append("#").append(tc).append(" ").append(heap.poll()[1]).append("\n");
		}

		System.out.print(output);
	}
}
