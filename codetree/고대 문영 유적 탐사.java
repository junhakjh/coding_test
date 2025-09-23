import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	static int K, M;
	static int[][] map;
	static int[] numbers;
	static int numbersIdx;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < 5 && c >= 0 && c < 5;
	}

	static boolean isIn(int r, int c, int mr, int mc) {
		return r >= mr - 1 && r <= mr + 1 && c >= mc - 1 && c <= mc + 1;
	}

	static int[][] rotate(int mr, int mc, int i) {
		int[][] newMap = new int[5][5];
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				if (isIn(r, c, mr, mc)) {
					int dr = r - mr, dc = c - mc;
					if (i == 1) {
						newMap[r][c] = map[mr - dc][mc + dr];
					} else if (i == 2) {
						newMap[r][c] = map[mr - dr][mc - dc];
					} else if (i == 3) {
						newMap[r][c] = map[mr + dc][mc - dr];
					}
				} else {
					newMap[r][c] = map[r][c];
				}
			}
		}
		return newMap;
	}

	static int getScore(int[][] map) {
		int sum = 0;

		boolean[][] visited = new boolean[5][5];
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				if (visited[r][c]) {
					continue;
				}
				visited[r][c] = true;
				int num = map[r][c];
				List<int[]> list = new ArrayList<>();
				Queue<int[]> q = new ArrayDeque<>();
				list.add(new int[] { r, c });
				q.offer(new int[] { r, c });
				while (!q.isEmpty()) {
					int[] item = q.poll();
					for (int i = 0; i < 4; i++) {
						int nr = item[0] + dr[i], nc = item[1] + dc[i];
						if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == num) {
							visited[nr][nc] = true;
							q.offer(new int[] { nr, nc });
							list.add(new int[] { nr, nc });
						}
					}
				}
				if (list.size() >= 3) {
					sum += list.size();
					for (int[] item : list) {
						map[item[0]][item[1]] = 0;
					}
				}
			}
		}

		return sum;
	}

	static void search() {
		int targetR = 1, targetC = 1, targetI = 1, max = 0;
		for (int i = 1; i <= 3; i++) {
			for (int c = 1; c <= 3; c++) {
				for (int r = 1; r <= 3; r++) {
					int[][] newMap = rotate(r, c, i);
					int score = getScore(newMap);
					if (score > max) {
						targetR = r;
						targetC = c;
						targetI = i;
						max = score;
					}
				}
			}
		}

		map = rotate(targetR, targetC, targetI);
	}

	static int getGoods() {
		int scoreSum = 0;
		while (true) {
			int score = getScore(map);
			if (score < 3) {
				break;
			}
			scoreSum += score;
			for (int c = 0; c < 5; c++) {
				for (int r = 4; r >= 0; r--) {
					if (map[r][c] == 0) {
						map[r][c] = numbers[numbersIdx++];
					}
				}
			}
		}

		return scoreSum;
	}

	static void solution() {
		numbersIdx = 0;
		for (int k = 0; k < K; k++) {
			search();
			int score = getGoods();
			if (score == 0) {
				break;
			}
			output.append(score).append(" ");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[5][5];
		for (int r = 0; r < 5; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < 5; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		numbers = new int[M];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < M; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		solution();

		System.out.println(output);
	}
}
