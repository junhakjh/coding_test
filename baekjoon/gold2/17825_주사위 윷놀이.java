import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] redDir = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 31, 21, 22, 28, 24,
			28, 26, 27, 28, 29, 30, 31, 32, 0 }, blueDir = { 0, 20, 23, 25 },
			scores = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 13, 16, 19, 22, 24,
					28, 27, 26, 25, 30, 35, 40, 0 };
	static final int FINAL = 32;

	static int[] nums, map;
	static int answer = 0;

	static void dfs(int idx, int scoreSum, int[] positions) {
		if (idx == 10) {
			answer = Math.max(answer, scoreSum);
			return;
		}

		label: for (int i = 0; i < 4; i++) {
			if (positions[i] == FINAL) {
				continue;
			}
			int prevPos = positions[i], nextPos = positions[i];
			for (int j = 0; j < nums[idx]; j++) {
				if (j == 0 && (nextPos == 5 || nextPos == 10 || nextPos == 15)) {
					nextPos = blueDir[nextPos / 5];
					continue;
				}
				nextPos = redDir[nextPos];
				if (nextPos == FINAL) {
					break;
				}
			}
			if (nextPos != FINAL) {
				for (int j = 0; j < 4; j++) {
					if (i != j && positions[j] == nextPos) {
						continue label;
					}
				}
			}
			positions[i] = nextPos;
			dfs(idx + 1, scoreSum + scores[nextPos], positions);
			positions[i] = prevPos;
		}
	}

	static int solution() {
		dfs(0, 0, new int[4]);

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		nums = new int[10];
		for (int i = 0; i < 10; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solution());
	}
}
