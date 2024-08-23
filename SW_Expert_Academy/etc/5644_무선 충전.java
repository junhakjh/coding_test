import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 23.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AWXRDL1aeugDFAUo&probBoxId=AZFnt7l6FYcDFAQW&type=PROBLEM&problemBoxTitle=0819%EC%A3%BC&problemBoxCnt=13
 * @timecomplex 
 * @performance	26,064 kb, 136 ms
 * @category #완전탐색
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, -1, 0, 1, 0 }, dc = { 0, 0, 1, 0, -1 };

	static int answer;
	static int totalTime, bcNum;
	static int[] aInfos, bInfos;
	static int[][] bcInfos;

	static void addSum(int[] a, int[] b) {
		List<int[]> aList = new ArrayList<>();
		List<int[]> bList = new ArrayList<>();
		for (int[] bcInfo : bcInfos) {
			int r = bcInfo[0] / 10, c = bcInfo[0] % 10;
			if (Math.abs(r - a[0]) + Math.abs(c - a[1]) <= bcInfo[1]) {
				aList.add(new int[] { bcInfo[0], bcInfo[2] });
			}
			if (Math.abs(r - b[0]) + Math.abs(c - b[1]) <= bcInfo[1]) {
				bList.add(new int[] { bcInfo[0], bcInfo[2] });
			}
		}
		int sum = 0;
		if (aList.size() == 0) {
			for (int[] bInfo : bList) {
				sum = Math.max(sum, bInfo[1]);
			}
		} else if (bList.size() == 0) {
			for (int[] aInfo : aList) {
				sum = Math.max(sum, aInfo[1]);
			}
		} else {
			for (int[] aInfo : aList) {
				for (int[] bInfo : bList) {
					if (aInfo[0] == bInfo[0]) {
						sum = Math.max(sum, aInfo[1]);
					} else {
						sum = Math.max(sum, aInfo[1] + bInfo[1]);
					}
				}
			}

		}
		answer += sum;
	}

	static void solution() {
		int[] a = { 0, 0 }, b = { 9, 9 };

		addSum(a, b);

		for (int t = 0; t < totalTime; t++) {
			a[0] += dr[aInfos[t]];
			a[1] += dc[aInfos[t]];
			b[0] += dr[bInfos[t]];
			b[1] += dc[bInfos[t]];
			addSum(a, b);
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			st = new StringTokenizer(input.readLine());
			totalTime = Integer.parseInt(st.nextToken());
			bcNum = Integer.parseInt(st.nextToken());
			aInfos = new int[totalTime];
			bInfos = new int[totalTime];
			bcInfos = new int[bcNum][3];
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < totalTime; i++) {
				aInfos[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < totalTime; i++) {
				bInfos[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < bcNum; i++) {
				st = new StringTokenizer(input.readLine());
				int c = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				int id = r * 10 + c;
				int cRange = Integer.parseInt(st.nextToken());
				int cAmount = Integer.parseInt(st.nextToken());
				bcInfos[i] = new int[] { id, cRange, cAmount };
			}

			solution();

			output.append("#").append(tc).append(" ").append(answer).append("\n");
			solution();
		}

		System.out.print(output);
	}
}
