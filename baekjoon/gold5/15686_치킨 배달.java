import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M, chickensNum;
	static int[][] map;
	static List<int[]> houses, chickens;
	static int answer = Integer.MAX_VALUE;

	static int getChickenDist() {
		int distSum = 0;

		for (int[] house : houses) {
			label: for (int dist = 1; dist <= 2 * N; dist++) {
				for (int dr = 0; dr <= dist; dr++) {
					int dc = dist - dr;
					if (house[0] + dr < N && house[1] + dc < N && map[house[0] + dr][house[1] + dc] == 2) {
						distSum += dist;
						break label;
					}
					if (house[0] + dr < N && house[1] - dc >= 0 && map[house[0] + dr][house[1] - dc] == 2) {
						distSum += dist;
						break label;
					}
					if (house[0] - dr >= 0 && house[1] + dc < N && map[house[0] - dr][house[1] + dc] == 2) {
						distSum += dist;
						break label;
					}
					if (house[0] - dr >= 0 && house[1] - dc >= 0 && map[house[0] - dr][house[1] - dc] == 2) {
						distSum += dist;
						break label;
					}
				}
			}
		}

		return distSum;
	}

	static void dfs(int curI, int aliveNum, boolean[] aliveChickens) {
		if (curI == chickensNum) {
			for (int i = 0; i < chickensNum; i++) {
				if (!aliveChickens[i]) {
					map[chickens.get(i)[0]][chickens.get(i)[1]] = 0;
				}
			}

			answer = Math.min(answer, getChickenDist());

			for (int i = 0; i < chickensNum; i++) {
				if (!aliveChickens[i]) {
					map[chickens.get(i)[0]][chickens.get(i)[1]] = 2;
				}
			}

			return;
		}

		aliveChickens[curI] = true;
		// 나머지 다 죽여야 하는 경우 고려
		dfs(aliveNum + 1 >= M ? chickensNum : curI + 1, aliveNum + 1, aliveChickens);
		aliveChickens[curI] = false;
		// 나머지를 다 살려야 하는 경우는 false 적용 후 dfs하지 않음
		if (chickensNum - curI > M - aliveNum) {
			dfs(curI + 1, aliveNum, aliveChickens);
		}
	}

	static int solution() {
		dfs(0, 0, new boolean[chickensNum]);

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				if (num == 1) {
					houses.add(new int[] { r, c });
				}
				if (num == 2) {
					chickens.add(new int[] { r, c });
					chickensNum++;
				}
			}
		}

		System.out.println(solution());
	}
}
