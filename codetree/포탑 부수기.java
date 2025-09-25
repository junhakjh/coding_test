import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1, 1, 1, -1, -1 }, dc = { 1, 0, -1, 0, 1, -1, -1, 1 };

	static int N, M, K;
	static int[][] map, attackMap;

	static int[] selectAttacker(int k) {
		int targetR = -1, targetC = -1;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					continue;
				}
				if (targetR == -1 && targetC == -1) {
					targetR = r;
					targetC = c;
					continue;
				}
				if (map[r][c] < map[targetR][targetC]) {
					targetR = r;
					targetC = c;
				}
				if (map[r][c] == map[targetR][targetC]) {
					if (attackMap[r][c] > attackMap[targetR][targetC]) {
						targetR = r;
						targetC = c;
					}
					if (attackMap[r][c] == attackMap[targetR][targetC]) {
						if (r + c > targetR + targetC) {
							targetR = r;
							targetC = c;
						}
						if (r + c == targetR + targetC) {
							if (c > targetC) {
								targetR = r;
								targetC = c;
							}
						}
					}
				}

			}
		}

		map[targetR][targetC] += N + M;
		attackMap[targetR][targetC] = k;

		return new int[] { targetR, targetC };
	}

	static int[] selectTarget(int[] attacker) {
		int targetR = -1, targetC = -1;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0 || r == attacker[0] && c == attacker[1]) {
					continue;
				}
				if (targetR == -1 && targetC == -1) {
					targetR = r;
					targetC = c;
					continue;
				}
				if (map[r][c] > map[targetR][targetC]) {
					targetR = r;
					targetC = c;
				}
				if (map[r][c] == map[targetR][targetC]) {
					if (attackMap[r][c] < attackMap[targetR][targetC]) {
						targetR = r;
						targetC = c;
					}
					if (attackMap[r][c] == attackMap[targetR][targetC]) {
						if (r + c < targetR + targetC) {
							targetR = r;
							targetC = c;
						}
						if (r + c == targetR + targetC) {
							if (c < targetC) {
								targetR = r;
								targetC = c;
							}
						}
					}
				}

			}
		}

		return new int[] { targetR, targetC };
	}

	static void laser(Set<Integer> set, int[] attacker, int[] target, int[][] track) {
		int power = map[attacker[0]][attacker[1]] / 2;
		int r = target[0], c = target[1];
		while (!(r == attacker[0] && c == attacker[1])) {
			int di = track[r][c];
			r = (r + dr[di] + N) % N;
			c = (c + dc[di] + M) % M;
			if (r == attacker[0] && c == attacker[1]) {
				break;
			}
			set.add(r * M + c);
			map[r][c] = Math.max(0, map[r][c] - power);
		}
	}

	static void cannon(Set<Integer> set, int[] attacker, int[] target) {
		int power = map[attacker[0]][attacker[1]] / 2;
		for (int i = 0; i < 8; i++) {
			int r = (target[0] + dr[i] + N) % N, c = (target[1] + dc[i] + M) % M;
			if ((r == attacker[0] && c == attacker[1]) || map[r][c] == 0) {
				continue;
			}
			map[r][c] = Math.max(0, map[r][c] - power);
			set.add(r * M + c);
		}
	}

	static Set<Integer> attack(int[] attacker) {
		Set<Integer> set = new HashSet<>();
		set.add(attacker[0] * M + attacker[1]);
		int[] target = selectTarget(attacker);
		set.add(target[0] * M + target[1]);

		int[][] track = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(attacker);
		visited[attacker[0]][attacker[1]] = true;
		label: while (!q.isEmpty()) {
			int[] item = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = (item[0] + dr[i] + N) % N, nc = (item[1] + dc[i] + M) % M;
				if (map[nr][nc] == 0 || visited[nr][nc]) {
					continue;
				}
				visited[nr][nc] = true;
				track[nr][nc] = (i + 2) % 4;
				if (nr == target[0] && nc == target[1]) {
					break label;
				}
				q.offer(new int[] { nr, nc });
			}
		}

		map[target[0]][target[1]] = Math.max(0, map[target[0]][target[1]] - map[attacker[0]][attacker[1]]);
		if (visited[target[0]][target[1]]) {
			laser(set, attacker, target, track);
		} else {
			cannon(set, attacker, target);
		}

		return set;
	}

	static boolean repair(Set<Integer> set) {
		int alive = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] != 0) {
					alive++;
				}
				if (map[r][c] == 0 || set.contains(r * M + c)) {
					continue;
				}
				map[r][c]++;
			}
		}
		
		return alive > 1;
	}

	static int solution() {
		int answer = 0;

		for (int k = 1; k <= K; k++) {
			int[] attacker = selectAttacker(k);
			Set<Integer> set = attack(attacker);
			if(!repair(set)) {
				break;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				answer = Math.max(answer, map[r][c]);
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		attackMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
	}
}
