import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	static int R, C, boxNum;
	static char[][] map;
	static char[] commands;
	static int[] player;

	static void solution(int tc) {
		for (char cmd : commands) {
			int r = player[0], c = player[1], di = cmd == 'U' ? 0 : cmd == 'R' ? 1 : cmd == 'D' ? 2 : 3,
					nr = r + dr[di], nc = c + dc[di];
			char origin = map[r][c], next = map[nr][nc];
			if (next == '.' || next == '+') { // player만 움직이는 경우
				map[r][c] = origin == 'W' ? '+' : '.';
				map[nr][nc] = next == '+' ? 'W' : 'w';
				player = new int[] { nr, nc };
			} else if (next == 'b' || next == 'B') { // box 움직이려 하는 경우
				int nnr = nr + dr[di], nnc = nc + dc[di];
				char nnext = map[nnr][nnc];
				if (nnext == '.' || nnext == '+') {
					map[r][c] = origin == 'W' ? '+' : '.';
					map[nr][nc] = next == 'B' ? 'W' : 'w';
					map[nnr][nnc] = nnext == '+' ? 'B' : 'b';
					player = new int[] { nr, nc };
					if (next == 'B' && nnext == '.') {
						boxNum++;
					} else if (next == 'b' && nnext == '+') {
						boxNum--;
					}
					if (boxNum == 0) {
						break;
					}
				}
			}
		}
		StringBuilder mapSB = new StringBuilder();
		for (char[] row : map) {
			for (char chr : row) {
				mapSB.append(chr);
			}
			mapSB.append("\n");
		}
		output.append("Game ").append(tc).append(": ").append(boxNum == 0 ? "complete" : "incomplete").append("\n")
				.append(mapSB);

	}

	public static void main(String[] args) throws Exception {
		int tc = 1;
		while (true) {
			st = new StringTokenizer(input.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			boxNum = 0;
			if (R == 0 && C == 0) {
				break;
			}
			map = new char[R][C];
			for (int r = 0; r < R; r++) {
				String str = input.readLine();
				for (int c = 0; c < C; c++) {
					map[r][c] = str.charAt(c);
					if (map[r][c] == 'w' || map[r][c] == 'W') {
						player = new int[] { r, c };
					}
					if (map[r][c] == 'b') {
						boxNum++;
					}
				}
			}
			commands = input.readLine().toCharArray();

			solution(tc++);
		}

		System.out.println(output);
	}
}
