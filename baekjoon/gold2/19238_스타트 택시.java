import java.util.*;
import java.io.*;

class Taxi implements Comparable<Taxi> {
	int r, c, fuel, dist;

	Taxi(int r, int c, int fuel) {
		this.r = r;
		this.c = c;
		this.fuel = fuel;
		this.dist = 0;
	}

	Taxi(int r, int c, int fuel, int dist) {
		this.r = r;
		this.c = c;
		this.fuel = fuel;
		this.dist = dist;
	}

	public int compareTo(Taxi t) {
		if (this.dist == t.dist) {
			if (this.r == t.r) {
				return Integer.compare(this.c, t.c);
			}
			return Integer.compare(this.r, t.r);
		}
		return Integer.compare(this.dist, t.dist);
	}

	public String toString() {
		return "r: " + this.r + ", " + "c: " + this.c + ", fuel: " + this.fuel + ", dist: " + this.dist;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 0, 0, 1 }, dc = { 0, -1, 1, 0 };

	static int N, M, F;
	static Taxi taxi;
	static boolean[][] map;
	static Map<Integer, LinkedList<Integer>> passengerMap;
	static Map<Integer, Set<Integer>> targetMap;

	static boolean isIn(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}

	static int solution() {
		label: while (M > 0) {
			Queue<Taxi> pq = new PriorityQueue<>();
			Queue<Taxi> q = new ArrayDeque<>();
			taxi.dist = 0;
			pq.offer(taxi);
			int target = 0;
			boolean[][] visited = new boolean[N + 1][N + 1];
			visited[taxi.r][taxi.c] = true;
			while (!pq.isEmpty()) {
				Taxi curTaxi = pq.poll();
				if (curTaxi.fuel == 0) {
					continue;
				}
				if (passengerMap.get(curTaxi.r * N + curTaxi.c).size() > 0) {
					curTaxi.dist = 0;
					q.offer(curTaxi);
					target = (-1) * passengerMap.get(curTaxi.r * N + curTaxi.c).pollLast();
					visited = new boolean[N + 1][N + 1];
					visited[curTaxi.r][curTaxi.c] = true;

					break;
				}
				for (int i = 0; i < 4; i++) {
					int nr = curTaxi.r + dr[i], nc = curTaxi.c + dc[i];
					if (isIn(nr, nc) && !visited[nr][nc] && !map[nr][nc]) {
						visited[nr][nc] = true;
						pq.offer(new Taxi(nr, nc, curTaxi.fuel - 1, curTaxi.dist + 1));
					}
				}
			}

			if (q.isEmpty()) {
				return -1;
			}
			while (!q.isEmpty()) {
				Taxi curTaxi = q.poll();
				if (targetMap.get(curTaxi.r * N + curTaxi.c).contains(target)) {
					targetMap.get(curTaxi.r * N + curTaxi.c).remove(target);
					M--;
					taxi = new Taxi(curTaxi.r, curTaxi.c, curTaxi.fuel + 2 * curTaxi.dist);
					continue label;
				}
				if (curTaxi.fuel == 0) {
					continue;
				}
				for (int i = 0; i < 4; i++) {
					int nr = curTaxi.r + dr[i], nc = curTaxi.c + dc[i];
					if (isIn(nr, nc) && !visited[nr][nc] && !map[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new Taxi(nr, nc, curTaxi.fuel - 1, curTaxi.dist + 1));
					}
				}
			}

			return -1;
		}

		return M == 0 ? taxi.fuel : -1;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new boolean[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(input.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()) == 1;
			}
		}
		st = new StringTokenizer(input.readLine());
		taxi = new Taxi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), F);
		passengerMap = new HashMap<>();
		targetMap = new HashMap<>();
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				passengerMap.put(r * N + c, new LinkedList<>());
				targetMap.put(r * N + c, new HashSet<>());
			}
		}
		for (int i = 2; i < 2 + M; i++) {
			st = new StringTokenizer(input.readLine());
			passengerMap.get(Integer.parseInt(st.nextToken()) * N + Integer.parseInt(st.nextToken())).add(i);
			targetMap.get(Integer.parseInt(st.nextToken()) * N + Integer.parseInt(st.nextToken())).add((-1) * i);
		}

		System.out.println(solution());
	}
}
