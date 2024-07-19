import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	static int n;
	static int m;
	static int sx;
	static int sy;
	static int k;
	static int[][] map;
	static int[] order;
	static int[] dice = { 0, 0, 0, 0, 0, 0 };
	static final int[][] mapper = { { 2, 1, 5, 3, 0, 4 }, { 4, 1, 0, 3, 5, 2 }, { 3, 0, 2, 5, 4, 1 },
			{ 1, 5, 2, 0, 4, 3 } };
	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, -1, 1 };

	static String solution(int[] curPos) {
		for (int i = 0; i < k; i++) {
			int dir = order[i];
			int nx = curPos[0] + dx[dir - 1];
			int ny = curPos[1] + dy[dir - 1];
			if (nx < 0 || nx >= m || ny < 0 || ny >= n)
				continue;
			curPos = new int[] { nx, ny };

			int[] tempDice = Arrays.copyOf(dice, 6);
			for (int j = 0; j < 6; j++) {
				dice[j] = tempDice[mapper[dir - 1][j]];
			}
			output.append(dice[5]).append("\n");
			
			int num = map[curPos[1]][curPos[0]];
			if(num == 0) {
				map[curPos[1]][curPos[0]] = dice[0];
			} else {
				dice[0] = num;
				map[curPos[1]][curPos[0]] = 0;
			}
		}

		return output.toString();
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		order = new int[k];

		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(input.readLine());
			for (int x = 0; x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < k; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution(new int[] { sx, sy }));
	}
}
