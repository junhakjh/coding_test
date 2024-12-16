import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[][] scores = { { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 },
			{ 0, 13, 16, 19, 25, 30, 35, 40 }, { 0, 22, 24, 25, 30, 35, 40 }, { 0, 28, 27, 26, 25, 30, 35, 40 } };

	static int[] dice;
	static int score;

	static void dfs(int turn, int[][] positions, int curScore) {
		if (turn == 10) {
			score = Math.max(score, curScore);
			return;
		}

		label: for (int i = 0; i < 4; i++) {
			if (positions[i][0] == -1) {
				continue;
			}
			int pos0 = positions[i][0], pos1 = positions[i][1];
			if (pos0 == 0 && pos1 != 0 && pos1 != 20 && pos1 % 5 == 0) {
				positions[i] = new int[] { pos1 / 5, 0 };
			}
			for (int[] pos : positions) {
				if (positions[i][1] + dice[turn] < scores[positions[i][0]].length && pos[0] >= 0) {
					if ((pos[0] == 0 && positions[i][0] == 0 && pos[1] == positions[i][1] + dice[turn])
							|| (pos[0] != 0 && positions[i][0] != 0
									&& scores[pos[0]][pos[1]] == scores[positions[i][0]][positions[i][1] + dice[turn]])
							|| (scores[pos[0]][pos[1]] == 40
									&& scores[positions[i][0]][positions[i][1] + dice[turn]] == 40)) {
						positions[i][0] = pos0;
						positions[i][1] = pos1;
						continue label;
					}
				}
			}
			int addScore = 0;
			positions[i][1] += dice[turn];
			if (positions[i][1] >= scores[positions[i][0]].length) {
				positions[i][0] = -1;
			} else {
				addScore = scores[positions[i][0]][positions[i][1]];
			}
			dfs(turn + 1, positions, curScore + addScore);
			positions[i][0] = pos0;
			positions[i][1] = pos1;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		dice = new int[10];
		score = 0;
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, 0);

		System.out.println(score);
	}
}
