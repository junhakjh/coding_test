import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M, K;
	static boolean[][] map;
	static boolean[][][] stickers;

	static boolean[][] rotate(boolean[][] sticker, int i) {
		if (i == 0) {
			return sticker;
		}
		int R = sticker.length, C = sticker[0].length;
		boolean[][] rotate;
		switch (i) {
		case 1:
		case 3:
			rotate = new boolean[C][R];
			break;
		default:
			rotate = new boolean[R][C];
			break;
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				switch (i) {
				case 1:
					rotate[c][R - r - 1] = sticker[r][c];
					break;
				case 2:
					rotate[R - r - 1][C - c - 1] = sticker[r][c];
					break;
				case 3:
					rotate[C - c - 1][r] = sticker[r][c];
					break;
				}

			}
		}

		return rotate;
	}

	static int solution() {
		int answer = 0;

		for (boolean[][] sticker : stickers) {
			outer: for (int i = 0; i < 4; i++) {
				boolean[][] rotated = rotate(sticker, i);
				int R = rotated.length, C = rotated[0].length;
				for (int sr = 0; sr <= N - R; sr++) {
					label: for (int sc = 0; sc <= M - C; sc++) {
						for (int r = 0; r < R; r++) {
							for (int c = 0; c < C; c++) {
								if (map[r + sr][c + sc] && rotated[r][c]) {
									continue label;
								}
							}
						}
						for (int r = 0; r < R; r++) {
							for (int c = 0; c < C; c++) {
								if (rotated[r][c]) {
									map[r + sr][c + sc] = true;
								}
							}
						}
						break outer;
					}
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c]) {
					answer++;
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		stickers = new boolean[K][][];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(input.readLine());
			int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
			boolean[][] sticker = new boolean[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(input.readLine());
				for (int c = 0; c < C; c++) {
					sticker[r][c] = st.nextToken().equals("1") ? true : false;
				}
			}
			stickers[i] = sticker;
		}

		System.out.println(solution());
	}
}
