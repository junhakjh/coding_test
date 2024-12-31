import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 }, dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int N, M;
	static int[][] map, orders;

	static boolean isIn(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}
		return true;
	}

	static List<int[]> moveCloud(List<int[]> cloud, int[] order) {
		List<int[]> list = new ArrayList<>();
		for (int[] coor : cloud) {
			int nr = (coor[0] + (dr[order[0]] * order[1]) + N) % N, nc = (coor[1] + (dc[order[0]] * order[1]) + N) % N;
			map[nr][nc] += 1;
			list.add(new int[] { nr, nc });
		}
		return list;
	}

	static void copyWater(List<int[]> targets) {
		for (int[] target : targets) {
			int num = 0;
			for (int i = 0; i < 4; i++) {
				int nr = target[0] + dr[2 * i + 1], nc = target[1] + dc[2 * i + 1];
				if (isIn(nr, nc) && map[nr][nc] > 0) {
					num++;
				}
			}
			map[target[0]][target[1]] += num;
		}
	}

	static List<int[]> makeClouds(List<int[]> deletedClouds) {
		List<int[]> list = new ArrayList<>();
		boolean[][] deleted = new boolean[N][N];
		for (int[] coor : deletedClouds) {
			deleted[coor[0]][coor[1]] = true;
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!deleted[r][c] && map[r][c] >= 2) {
					map[r][c] -= 2;
					list.add(new int[] { r, c });
				}
			}
		}

		return list;
	}

	static int solution() {
		int result = 0;
		List<int[]> cloud = new ArrayList<>();
		cloud.add(new int[] { N - 2, 0 });
		cloud.add(new int[] { N - 2, 1 });
		cloud.add(new int[] { N - 1, 0 });
		cloud.add(new int[] { N - 1, 1 });
		for (int[] order : orders) {
			List<int[]> deletedClouds = moveCloud(cloud, order);
			copyWater(deletedClouds);
			cloud = makeClouds(deletedClouds);
		}
		for (int[] row : map) {
			for (int cell : row) {
				result += cell;
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		orders = new int[M][2];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			orders[i][0] = Integer.parseInt(st.nextToken()) - 1;
			orders[i][1] = Integer.parseInt(st.nextToken()) % N;
		}

		System.out.println(solution());
	}
}
