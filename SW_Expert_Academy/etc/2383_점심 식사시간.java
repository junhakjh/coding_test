import java.util.*;
import java.io.*;

class Stair {
	int r;
	int c;
	int length;
	int[] startTimes;
	Queue<Integer> waiting;
	int peopleNum;

	Stair(int r, int c, int length) {
		this.r = r;
		this.c = c;
		this.length = length;
	}

	void init() {
		startTimes = new int[3];
		waiting = new PriorityQueue<>();
		peopleNum = 0;
	}
}

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[][] map;
	static Stair[] stairs;
	static List<Integer> peopleIds;
	static int peopleNum;
	static int[] people;

	static int answer;

	static void dfs(int depth) {
		if (depth == peopleNum) {
			for (Stair stair : stairs) {
				stair.init();
			}
			for (int i = 0; i < peopleNum; i++) {
				int r = peopleIds.get(i) / n, c = peopleIds.get(i) % n;
				Stair stair = stairs[people[i]];
				stair.waiting.offer(Math.abs(r - stair.r) + Math.abs(c - stair.c));
				stair.peopleNum++;
			}
			int maxT = 0;
			for (Stair stair : stairs) {
				if (stair.peopleNum == 0) {
					continue;
				}
				int distance = stair.waiting.poll();
				int t = 0;
				while (stair.peopleNum > 0) {
					t++;
					for (int i = 0; i < 3; i++) {
						if (stair.startTimes[i] != 0 && t - stair.startTimes[i] >= stair.length) {
							stair.peopleNum--;
							stair.startTimes[i] = 0;
						}
						if (stair.startTimes[i] == 0 && distance != -1) {
							if (distance < t) {
								stair.startTimes[i] = t;
								if (stair.waiting.isEmpty()) {
									distance = -1;
								} else {
									distance = stair.waiting.poll();
								}
							}
						}
					}
				}
				maxT = Math.max(maxT, t);
			}
			answer = Math.min(answer, maxT);

			return;
		}

		people[depth] = 0;
		dfs(depth + 1);
		people[depth] = 1;
		dfs(depth + 1);
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			n = Integer.parseInt(input.readLine());
			map = new int[n][n];
			stairs = new Stair[2];
			peopleIds = new ArrayList<>();
			int stairNum = 0;
			for (int r = 0; r < n; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < n; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] >= 2) {
						stairs[stairNum++] = new Stair(r, c, map[r][c]);
					} else if (map[r][c] == 1) {
						peopleIds.add(r * n + c);
					}
				}
			}
			peopleNum = peopleIds.size();
			people = new int[peopleNum];

			dfs(0);

			output.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(output);
	}
}
