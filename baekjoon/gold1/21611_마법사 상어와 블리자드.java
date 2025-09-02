import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;
	static int[][] blizards;
	static int answer = 0;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static void iceAttack(int di, int num) {
		int r = N / 2, c = N / 2;
		for (int n = 1; n <= num; n++) {
			r += dr[di];
			c += dc[di];
			map[r][c] = 4;
		}
	}

	static Queue<Integer> getQueue() {
		Queue<Integer> q = new ArrayDeque<>();

		int r = N / 2, c = N / 2;
		q.offer(0);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				int di = i % 2 == 1 ? 2 : 3;
				r += dr[di];
				c += dc[di];
				if (map[r][c] == 0) {
					return q;
				}
				if (map[r][c] != 4) {
					q.offer(map[r][c]);
				}
			}
			for (int j = 0; j < i; j++) {
				int di = i % 2 == 1 ? 1 : 0;
				r += dr[di];
				c += dc[di];
				if (map[r][c] == 0) {
					return q;
				}
				if (map[r][c] != 4) {
					q.offer(map[r][c]);
				}
			}
		}
		for (c = N - 2; c >= 0; c--) {
			if (map[r][c] == 0) {
				return q;
			}
			if (map[r][c] != 4) {
				q.offer(map[r][c]);
			}
		}

		return q;
	}

	static ArrayDeque<Integer> explosion(Queue<Integer> q) {
		ArrayDeque<Integer> result;
		boolean isExplode;
		do {
			result = new ArrayDeque<>();
			isExplode = false;
			int prev = 0, cnt = 0;
			while (!q.isEmpty()) {
				int num = q.poll();
				if (num != prev) {
					if (cnt >= 4) {
						answer += prev * cnt;
						for (int i = 0; i < cnt; i++) {
							result.pollLast();
						}
						isExplode = true;
					}
					cnt = 0;
				}
				prev = num;
				cnt++;
				result.offerLast(num);
			}
			if (cnt >= 4) {
				answer += prev * cnt;
				for (int i = 0; i < cnt; i++) {
					result.pollLast();
				}
				isExplode = true;
			}
			q = result;
		} while (isExplode);

		return result;
	}

	static Queue<Integer> changeByGroup(Queue<Integer> q) {
		Queue<Integer> result = new ArrayDeque<>();
		result.offer(q.poll()); // 상어(0) 털기
		if (q.isEmpty()) {
			return result;
		}
		int prev = q.poll(), cnt = 1;
		while (!q.isEmpty()) {
			int num = q.poll();
			if (num != prev) {
				result.offer(cnt);
				result.offer(prev);
				cnt = 0;
			}
			prev = num;
			cnt++;
		}
		result.offer(cnt);
		result.offer(prev);

		return result;
	}

	static void updateMap(Queue<Integer> q) {
		int r = N / 2, c = N / 2;
		map[r][c] = q.poll();
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				int di = i % 2 == 1 ? 2 : 3;
				r += dr[di];
				c += dc[di];
				map[r][c] = q.isEmpty() ? 0 : q.poll();
			}
			for (int j = 0; j < i; j++) {
				int di = i % 2 == 1 ? 1 : 0;
				r += dr[di];
				c += dc[di];
				map[r][c] = q.isEmpty() ? 0 : q.poll();
			}
		}
		for (c = N - 2; c >= 0; c--) {
			map[r][c] = q.isEmpty() ? 0 : q.poll();
		}
	}

	static int solution() {
		for (int[] blizard : blizards) {
			iceAttack(blizard[0], blizard[1]);
			Queue<Integer> q = getQueue();
			ArrayDeque<Integer> explosionResult = explosion(q);
			Queue<Integer> resultQueue = changeByGroup(explosionResult);
			updateMap(resultQueue);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		blizards = new int[M][2];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			blizards[i] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };
		}

		System.out.println(solution());
	}
}
