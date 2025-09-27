// import java.util.*;
// import java.io.*;

// public class Main {
//     static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//     static StringBuilder output = new StringBuilder();
//     static StringTokenizer st;

//     static final int[] dr1 = {-1, 1, 0, 0}, dc1 = {0, 0, -1, 1}, dr2 = {0, 0, -1, 1}, dc2 = {-1, 1, 0, 0};
//     static final int[] reverseD = {1, 0, 3, 2};

//     static int N, M;
//     static int[] house, park, snake;
//     static ArrayDeque<int[]> soldiers;
//     static boolean[][] map;
//     static int[][] soldierMap;
//     static int[][] rockMap;
//     static int[] route;
//     static int totalMoves = -1;

//     static boolean isIn(int r, int c) {
//         return r >= 0 && r < N && c >= 0 && c < N;
//     }

//     static void findRoute() {
//         ArrayDeque<int[]> q = new ArrayDeque<>();
//         q.offer(new int[] {house[0], house[1], 0});
//         int[][] visited = new int[N][N];
//         loop: while (!q.isEmpty()) {
//             int[] pos = q.poll();
//             int r = pos[0], c = pos[1], num = pos[2];
//             for (int i = 0; i < 4; i++) {
//                 int nr = r + dr1[i], nc = c + dc1[i];
//                 if (isIn(nr, nc) && map[nr][nc]) {
//                     map[nr][nc] = false;
//                     visited[nr][nc] = reverseD[i];
//                     if(nr == park[0] && nc == park[1]) {
//                         totalMoves = num + 1;
//                         break loop;
//                     }
//                     q.offer(new int[] {nr, nc, num + 1});
//                 }
//             }
//         }
//         if(totalMoves == -1) {
//             return;
//         }
//         route = new int[totalMoves];
//         int r = park[0], c = park[1];
//         for (int i = totalMoves - 1; i >= 0; i--) {
//             int d = visited[r][c];
//             route[i] = reverseD[d];
//             r = r + dr1[d];
//             c = c + dc1[d];
//         }
//     }

//     static void snakeMove(int i) {
//         int d = route[i], r = snake[0] + dr1[d], c = snake[1] + dc1[d];
//         snake = new int[] {r, c};
//         soldierMap[r][c] = 0;
//     }

//     static int makeRock() {
//         int maxRock = 0;
//         int tempRock;
//         int[][] tempRockMap1 = new int[N][N];
//         tempRock = 0;
//         for (int r = snake[0] - 1; r >= 0; r--) {
//             int diff = snake[0] - r;
//             for (int c = Math.max(snake[1] - diff, 0); c <= Math.min(snake[1] + diff, N - 1); c++) {
//                 if (tempRockMap1[r][c] == 2) {
//                     continue;
//                 }
//                 tempRockMap1[r][c] = 1;
//                 if (soldierMap[r][c] > 0) {
//                     tempRock += soldierMap[r][c];
//                     for(int nr = r - 1; nr >= 0; nr--) {
//                         tempRockMap1[nr][c] = 2;
//                     }
//                     if(c > snake[1]) {
//                         for(int nr = r - 1; nr >= 0; nr--) {
//                             int tempDiff = r - nr;
//                             for(int nc = c + 1; nc <= Math.min(c + tempDiff, N - 1); nc++) {
//                                 tempRockMap1[nr][nc] = 2;
//                             }
//                         }
//                     }
//                     if(c < snake[1]) {
//                         for(int nr = r - 1; nr >= 0; nr--) {
//                             int tempDiff = r - nr;
//                             for(int nc = c - 1; nc >= Math.max(c - tempDiff, 0); nc--) {
//                                 tempRockMap1[nr][nc] = 2;
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         if(tempRock > maxRock) {
//             maxRock = tempRock;
//             rockMap = tempRockMap1;
//         }

//         int[][] tempRockMap2 = new int[N][N];
//         tempRock = 0;
//         for (int r = snake[0] + 1; r < N; r++) {
//             int diff = r - snake[0];
//             for (int c = Math.max(snake[1] - diff, 0); c <= Math.min(snake[1] + diff, N - 1); c++) {
//                 if (tempRockMap2[r][c] == 2) {
//                     continue;
//                 }
//                 tempRockMap2[r][c] = 1;
//                 if (soldierMap[r][c] > 0) {
//                     tempRock += soldierMap[r][c];
//                     for(int nr = r + 1; nr < N; nr++) {
//                         tempRockMap2[nr][c] = 2;
//                     }
//                     if(c > snake[1]) {
//                         for(int nr = r + 1; nr < N; nr++) {
//                             int tempDiff = nr - r;
//                             for(int nc = c + 1; nc <= Math.min(c + tempDiff, N - 1); nc++) {
//                                 tempRockMap2[nr][nc] = 2;
//                             }
//                         }
//                     }
//                     if(c < snake[1]) {
//                         for(int nr = r + 1; nr < N; nr++) {
//                             int tempDiff = nr - r;
//                             for(int nc = c - 1; nc >= Math.max(c - tempDiff, 0); nc--) {
//                                 tempRockMap2[nr][nc] = 2;
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         if(tempRock > maxRock) {
//             maxRock = tempRock;
//             rockMap = tempRockMap2;
//         }

//         int[][] tempRockMap3 = new int[N][N];
//         tempRock = 0;
//         for (int c = snake[1] - 1; c >= 0; c--) {
//             int diff = snake[1] - c;
//             for (int r = Math.max(snake[0] - diff, 0); r <= Math.min(snake[0] + diff, N - 1); r++) {
//                 if (tempRockMap3[r][c] == 2) {
//                     continue;
//                 }
//                 tempRockMap3[r][c] = 1;
//                 if (soldierMap[r][c] > 0) {
//                     tempRock += soldierMap[r][c];
//                     for(int nc = c - 1; nc >= 0; nc--) {
//                         tempRockMap3[r][nc] = 2;
//                     }
//                     if(r < snake[0]) {
//                         for(int nc = c - 1; nc >= 0; nc--) {
//                             int tempDiff = c - nc;
//                             for(int nr = r - 1; nr >= Math.max(r - tempDiff, 0); nr--) {
//                                 tempRockMap3[nr][nc] = 2;
//                             }
//                         }
//                     }
//                     if(r > snake[0]) {
//                         for(int nc = c - 1; nc >= 0; nc--) {
//                             int tempDiff = c - nc;
//                             for(int nr = r + 1; nr <= Math.min(r + tempDiff, N - 1); nr++) {
//                                 tempRockMap3[nr][nc] = 2;
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         if(tempRock > maxRock) {
//             maxRock = tempRock;
//             rockMap = tempRockMap3;
//         }

//         int[][] tempRockMap4 = new int[N][N];
//         tempRock = 0;
//         for (int c = snake[1] + 1; c < N; c++) {
//             int diff = c - snake[1];
//             for (int r = Math.max(snake[0] - diff, 0); r <= Math.min(snake[0] + diff, N - 1); r++) {
//                 if (tempRockMap4[r][c] == 2) {
//                     continue;
//                 }
//                 tempRockMap4[r][c] = 1;
//                 if (soldierMap[r][c] > 0) {
//                     tempRock += soldierMap[r][c];
//                     for(int nc = c + 1; nc < N; nc++) {
//                         tempRockMap4[r][nc] = 2;
//                     }
//                     if(r < snake[0]) {
//                         for(int nc = c + 1; nc < N; nc++) {
//                             int tempDiff = nc - c;
//                             for(int nr = r - 1; nr >= Math.max(r - tempDiff, 0); nr--) {
//                                 tempRockMap4[nr][nc] = 2;
//                             }
//                         }
//                     }
//                     if(r > snake[0]) {
//                         for(int nc = c + 1; nc < N; nc++) {
//                             int tempDiff = nc - c;
//                             for(int nr = r + 1; nr <= Math.min(r + tempDiff, N - 1); nr++) {
//                                 tempRockMap4[nr][nc] = 2;
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         if(tempRock > maxRock) {
//             maxRock = tempRock;
//             rockMap = tempRockMap4;
//         }
        
//         return maxRock;
//     }

//     static boolean isMoreNear(int r, int c, int nr, int nc, int sr, int sc) {
//         return Math.abs(sr - r) + Math.abs(sc - c) > Math.abs(sr - nr) + Math.abs(sc - nc);
//     }

//     static int[] moveAndAttack() {
//         int moves = 0, deathNum = 0;
//         int length = soldiers.size();
//         loop: for(int t = 0; t < length; t++) {
//             int[] soldier = soldiers.poll();
//             int r = soldier[0], c = soldier[1];
//             if(soldierMap[r][c] == 0) {
//                 continue;
//             }
//             if(rockMap[r][c] == 1) {
//                 soldiers.offer(soldier);
//                 continue;
//             }
//             for(int i = 0; i < 4; i++) {
//                 int nr = r + dr1[i], nc = c + dc1[i];
//                 if(isIn(nr, nc) && isMoreNear(r, c, nr, nc, snake[0], snake[1]) && rockMap[nr][nc] != 1) {
//                     moves += 1;
//                     // System.out.println(r + ", " + c + ", " + nr + ", " + nc);
//                     soldierMap[r][c] -= 1;
//                     r = nr;
//                     c = nc;
//                     if(r == snake[0] && c == snake[1]) {
//                         deathNum += 1;
//                         continue loop;
//                     }
//                     soldierMap[r][c] += 1;
//                     break;
//                 }
//             }
//             for(int i = 0; i < 4; i++) {
//                 int nr = r + dr2[i], nc = c + dc2[i];
//                 if(isIn(nr, nc) && isMoreNear(r, c, nr, nc, snake[0], snake[1]) && rockMap[nr][nc] != 1) {
//                     moves += 1;
//                     soldierMap[r][c] -= 1;
//                     r = nr;
//                     c = nc;
//                     if(r == snake[0] && c == snake[1]) {
//                         deathNum += 1;
//                         continue loop;
//                     }
//                     soldierMap[r][c] += 1;
//                     break;
//                 }
//             }
//             soldiers.offer(new int[] {r, c});
//         }

//         return new int[] {moves, deathNum};
//     }

//     static void solution() {
//         findRoute();
//         if(totalMoves == -1) {
//             output.append("-1");
//             return;
//         }
//         for(int i = 0; i < route.length - 1; i++) {
//             snakeMove(i);
//             int rockSoldiers = makeRock();
//             int[] result = moveAndAttack();
//             output.append(result[0]).append(" ").append(rockSoldiers).append(" ").append(result[1]).append("\n");
//         }
//         output.append("0");
//     }

//     public static void main(String[] args) throws Exception {
//         st = new StringTokenizer(input.readLine());
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         st = new StringTokenizer(input.readLine());
//         house = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
//         snake = house;
//         park = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
//         soldiers = new ArrayDeque<>();
//         soldierMap = new int[N][N];
//         st = new StringTokenizer(input.readLine());
//         for(int i = 0; i < M; i++) {
//             int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
//             soldiers.offer(new int[] {r, c});
//             soldierMap[r][c] += 1;
//         }
//         map = new boolean[N][N];
//         for(int r = 0; r < N; r++) {
//             st = new StringTokenizer(input.readLine());
//             for(int c = 0; c < N; c++) {
//                 map[r][c] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
//             }
//         }

//         solution();

//         System.out.println(output);
//     }
// }


import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	static int N, M, hr, hc, pr, pc;
	static int[][] map, soldierMap, distMap;

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static int getDist(int r, int c) {
		return Math.abs(r - hr) + Math.abs(c - hc);
	}

	static boolean getDistMap() {
		distMap = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		visited[pr][pc] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { pr, pc, 0 });
		boolean canArrive = false;
		while (!q.isEmpty()) {
			int[] item = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = item[0] + dr[i], nc = item[1] + dc[i];
				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
					visited[nr][nc] = true;
					distMap[nr][nc] = item[2] + 1;
					q.offer(new int[] { nr, nc, item[2] + 1 });
					if (nr == hr && nc == hc) {
						canArrive = true;
					}
				}
			}
		}

		return canArrive;
	}

	static void moveSnake() {
		int min = Integer.MAX_VALUE, ti = 0;
		for (int i = 0; i < 4; i++) {
			int nr = hr + dr[i], nc = hc + dc[i];
			if (isIn(nr, nc) && map[nr][nc] != 1 && distMap[nr][nc] < min) {
				min = distMap[nr][nc];
				ti = i;
			}
		}
		hr += dr[ti];
		hc += dc[ti];
		soldierMap[hr][hc] = 0;
	}

	static boolean[][] see() {
		boolean[][][] rockMap = new boolean[4][N][N], safeMap = new boolean[4][N][N];
		int maxRocks = 0, maxDi = 0;

		for (int di = 0; di < 4; di++) {
			int rocks = 0;

			if (di == 0) {
				for (int r = hr - 1; r >= 0; r--) {
					for (int c = hc - (hr - r); c <= hc + (hr - r); c++) {
						if (!isIn(r, c) || safeMap[di][r][c]) {
							continue;
						}
						rockMap[di][r][c] = true;
						if (soldierMap[r][c] > 0) {
							rocks += soldierMap[r][c];
							for (int nr = r - 1; nr >= 0; nr--) {
								for (int nc = (c < hc ? c - (r - nr) : c); nc <= (c > hc ? c + (r - nr) : c); nc++) {
									if (!isIn(nr, nc)) {
										continue;
									}
									safeMap[di][nr][nc] = true;
								}
							}
						}
					}
				}
			} else if (di == 1) {
				for (int r = hr + 1; r < N; r++) {
					for (int c = hc - (r - hr); c <= hc + (r - hr); c++) {
						if (!isIn(r, c) || safeMap[di][r][c]) {
							continue;
						}
						rockMap[di][r][c] = true;
						if (soldierMap[r][c] > 0) {
							rocks += soldierMap[r][c];
							for (int nr = r + 1; nr < N; nr++) {
								for (int nc = (c < hc ? c - (nr - r) : c); nc <= (c > hc ? c + (nr - r) : c); nc++) {
									if (!isIn(nr, nc)) {
										continue;
									}
									safeMap[di][nr][nc] = true;
								}
							}
						}
					}
				}
			} else if (di == 2) {
				for (int c = hc - 1; c >= 0; c--) {
					for (int r = hr - (hc - c); r <= hr + (hc - c); r++) {
						if (!isIn(r, c) || safeMap[di][r][c]) {
							continue;
						}
						rockMap[di][r][c] = true;
						if (soldierMap[r][c] > 0) {
							rocks += soldierMap[r][c];
							for (int nc = c - 1; nc >= 0; nc--) {
								for (int nr = (r < hr ? r - (c - nc) : r); nr <= (r > hr ? r + (c - nc) : r); nr++) {
									if (!isIn(nr, nc)) {
										continue;
									}
									safeMap[di][nr][nc] = true;
								}
							}
						}
					}
				}
			} else if (di == 3) {
				for (int c = hc + 1; c < N; c++) {
					for (int r = hr - (c - hc); r <= hr + (c - hc); r++) {
						if (!isIn(r, c) || safeMap[di][r][c]) {
							continue;
						}
						rockMap[di][r][c] = true;
						if (soldierMap[r][c] > 0) {
							rocks += soldierMap[r][c];
							for (int nc = c + 1; nc < N; nc++) {
								for (int nr = (r < hr ? r - (nc - c) : r); nr <= (r > hr ? r + (nc - c) : r); nr++) {
									if (!isIn(nr, nc)) {
										continue;
									}
									safeMap[di][nr][nc] = true;
								}
							}
						}
					}
				}
			}

			if (rocks > maxRocks) {
				maxRocks = rocks;
				maxDi = di;
			}
		}

		return rockMap[maxDi];
	}

	static void moveSoldiers(boolean[][] rockMap) {
		int rocks = 0, moves = 0, death = 0;
		int[][] newSoldierMap = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (soldierMap[r][c] == 0) {
					continue;
				}
				if (rockMap[r][c]) {
					rocks += soldierMap[r][c];
					newSoldierMap[r][c] += soldierMap[r][c];
					continue;
				}
				boolean flag = false;
				int nr = -1, nc = -1;
				for (int i = 0; i < 4; i++) {
					nr = r + dr[i];
					nc = c + dc[i];
					if (!isIn(nr, nc) || rockMap[nr][nc]) {
						continue;
					}
					if (getDist(nr, nc) < getDist(r, c)) {
						flag = true;
						moves += soldierMap[r][c];
						break;
					}
				}
				if (!flag) {
					newSoldierMap[r][c] += soldierMap[r][c];
					continue;
				}
				int tr = nr, tc = nc;
				if (tr == hr && tc == hc) {
					death += soldierMap[r][c];
					continue;
				}
				flag = false;
				for (int i = 2; i < 6; i++) {
					nr = tr + dr[i % 4];
					nc = tc + dc[i % 4];
					if (!isIn(nr, nc) || rockMap[nr][nc]) {
						continue;
					}
					if (getDist(nr, nc) < getDist(tr, tc)) {
						tr = nr;
						tc = nc;
						flag = true;
						break;
					}
				}
				if(flag) {					
					moves += soldierMap[r][c];
				}
				if (tr == hr && tc == hc) {
					death += soldierMap[r][c];
				} else {
					newSoldierMap[tr][tc] += soldierMap[r][c];
				}
			}
		}

		soldierMap = newSoldierMap;

		output.append(moves).append(" ").append(rocks).append(" ").append(death);
	}

	static void solution() {
		if (!getDistMap()) {
			output.append("-1");
			return;
		}
		while (!(hr == pr && hc == pc)) {
			moveSnake();
			if (hr == pr && hc == pc) {
				output.append("0");
				break;
			}
			boolean[][] rockMap = see();
			moveSoldiers(rockMap);
			output.append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(input.readLine());
		hr = Integer.parseInt(st.nextToken());
		hc = Integer.parseInt(st.nextToken());
		pr = Integer.parseInt(st.nextToken());
		pc = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(input.readLine());
		soldierMap = new int[N][N];
		for (int i = 0; i < M; i++) {
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
			soldierMap[r][c]++;
		}
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		map[pr][pc] = 2;

		solution();

		System.out.println(output);
	}
}
