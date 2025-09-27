import java.util.*;
import java.io.*;

class Team {
	int[] head, tail;
	int len;

	Team(int[] head, int[] tail, int len) {
		this.head = head;
		this.tail = tail;
		this.len = len;
	}

	void turn() {
		int hr = head[0], hc = head[1], tr = tail[0], tc = tail[1];
		this.head = new int[] { tr, tc };
		this.tail = new int[] { hr, hc };
	}

	public String toString() {
		return "head: " + Arrays.toString(head) + ", tail: " + Arrays.toString(tail) + ", len: " + len;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };
	static final int NONE = 0, HEAD = 1, BODY = 2, TAIL = 3, ROAD = 4;

	static int N, M, K;
	static int[][] map;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static Team[] getTeams() {
		Team[] teams = new Team[M + 1];
		int teamsIdx = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == HEAD) {
					int hr = r, hc = c, tr = r, tc = c, prevR = -1, prevC = -1, len = 1;
					while (map[tr][tc] != TAIL) {
						for (int i = 0; i < 4; i++) {
							int nr = tr + dr[i], nc = tc + dc[i];
							if (isIn(nr, nc) && !(nr == prevR && nc == prevC) && map[nr][nc] != NONE
									&& map[nr][nc] != ROAD) {
								if (map[tr][tc] == HEAD && map[nr][nc] == TAIL) {
									continue;
								}
								prevR = tr;
								prevC = tc;
								tr = nr;
								tc = nc;
								if (map[nr][nc] == BODY) {
									map[nr][nc] = (-1) * teamsIdx;
								}
								break;
							}
						}
						len++;
					}
					teams[teamsIdx++] = new Team(new int[] { hr, hc }, new int[] { tr, tc }, len);
				}
			}
		}

		return teams;
	}

	static void move(Team[] teams) {
		for (int teamIdx = 1; teamIdx <= M; teamIdx++) {
			Team team = teams[teamIdx];
			int hr = team.head[0], hc = team.head[1], tr = team.tail[0], tc = team.tail[1];
			for (int i = 0; i < 4; i++) {
				int nr = tr + dr[i], nc = tc + dc[i];
				if (isIn(nr, nc) && map[nr][nc] < 0) {
					map[nr][nc] = TAIL;
					map[tr][tc] = ROAD;
					team.tail = new int[] { nr, nc };
					break;
				}
			}
			for (int i = 0; i < 4; i++) {
				int nr = hr + dr[i], nc = hc + dc[i];
				if (isIn(nr, nc) && map[nr][nc] == ROAD) {
					map[nr][nc] = HEAD;
					map[hr][hc] = (-1) * teamIdx;
					team.head = new int[] { nr, nc };
					break;
				}
			}
		}
	}

	static int getScore(int r, int c, Team[] teams) {
		int teamIdx = (-1) * map[r][c];
		if (map[r][c] == HEAD || map[r][c] == TAIL) {
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (isIn(nr, nc) && map[nr][nc] < 0) {
					teamIdx = (-1) * map[nr][nc];
					break;
				}
			}
		}
		Team team = teams[teamIdx];
		int hr = team.head[0], hc = team.head[1], tr = team.tail[0], tc = team.tail[1];
		team.turn();
		int score = 0;
		if (map[r][c] == HEAD) {
			score = 1;
		} else if (map[r][c] == TAIL) {
			score = (int) Math.pow(team.len, 2);
		} else {
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] { r, c, r, c, 1 });
			label: while (!q.isEmpty()) {
				int[] item = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = item[0] + dr[i], nc = item[1] + dc[i];
					if (isIn(nr, nc) && !(nr == item[2] && nc == item[3])) {
						if (map[nr][nc] < 0) {
							q.offer(new int[] { nr, nc, item[0], item[1], item[4] + 1 });
						} else if (map[nr][nc] == HEAD) {
							score = (int) Math.pow(item[4] + 1, 2);
							break label;
						} else if (map[nr][nc] == TAIL) {
							score = (int) Math.pow(team.len - item[4], 2);
							break label;
						}
					}
				}
			}
		}
		map[hr][hc] = TAIL;
		map[tr][tc] = HEAD;

		return score;
	}

	static int throwBall(int turn, Team[] teams) {
		int di = (turn / N) % 4;
		if (di == 0) {
			int r = turn % (4 * N);
			for (int c = 0; c < N; c++) {
				if (map[r][c] == HEAD || map[r][c] < 0 || map[r][c] == TAIL) {
					return getScore(r, c, teams);
				}
			}
		} else if (di == 1) {
			int c = (turn - N) % (4 * N);
			for (int r = N - 1; r >= 0; r--) {
				if (map[r][c] == HEAD || map[r][c] < 0 || map[r][c] == TAIL) {
					return getScore(r, c, teams);
				}
			}
		} else if (di == 2) {
			int r = (N - 1) - (turn - 2 * N) % (4 * N);
			for (int c = N - 1; c >= 0; c--) {
				if (map[r][c] == HEAD || map[r][c] < 0 || map[r][c] == TAIL) {
					return getScore(r, c, teams);
				}
			}
		} else if (di == 3) {
			int c = (N - 1) - (turn - 3 * N) % (4 * N);
			for (int r = 0; r < N; r++) {
				if (map[r][c] == HEAD || map[r][c] < 0 || map[r][c] == TAIL) {
					return getScore(r, c, teams);
				}
			}
		}

		return 0;
	}

	static int solution() {
		int answer = 0;
		Team[] teams = getTeams();

		for (int k = 0; k < K; k++) {
			move(teams);
			answer += throwBall(k, teams);
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());
	}
}
