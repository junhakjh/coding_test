import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int k, n;
	static char[] orders;
	static int hollPosition;
	static int[][] map;

	static int getStart() {
		int l = 0, r = n, u = 0, d = n;
		for (char order : orders) {
			switch (order) {
			case 'R':
				l = (int) Math.ceil(((double) (l + r)) / 2);
				break;
			case 'L':
				r = (l + r) / 2;
				break;
			case 'D':
				u = (int) Math.ceil(((double) (u + d)) / 2);
				break;
			case 'U':
				d = (u + d) / 2;
				break;
			}
		}
		return u * n + l;
	}

	static void solution() {
		int id = getStart();
		int startR = id / n, startC = id % n;
		map[startR][startC] = hollPosition;
		int left = startC, right = left + 1, top = startR, bottom = top + 1;
		boolean row = false, col = false;
		for (int i = n - 1; i >= 0; i--) {
			char order = orders[i];
			switch (order) {
			case 'R':
				left -= (right - left);
				if (row) {
					for (int c = left; c < (left + right) / 2; c++) {
						for (int r = top; r < bottom; r++) {
							map[r][c] = map[r][c + (right - left) / 2];
						}
					}
				} else {
					for (int r = top; r < bottom; r++) {
						if (map[r][right - 1] % 2 == 0) {
							map[r][left] = map[r][right - 1] + 1;
						} else {
							map[r][left] = map[r][right - 1] - 1;
						}
					}
				}
				row = true;
				break;
			case 'L':
				right += (right - left);
				if (row) {
					for (int c = (left + right) / 2; c < right; c++) {
						for (int r = top; r < bottom; r++) {
							map[r][c] = map[r][c - (right - left) / 2];
						}
					}
				} else {
					for (int r = top; r < bottom; r++) {
						if (map[r][left] % 2 == 0) {
							map[r][right - 1] = map[r][left] + 1;
						} else {
							map[r][right - 1] = map[r][left] - 1;
						}
					}
				}
				row = true;
				break;
			case 'D':
				top -= (bottom - top);
				if (col) {
					for (int r = top; r < (top + bottom) / 2; r++) {
						for (int c = left; c < right; c++) {
							map[r][c] = map[r + (bottom - top) / 2][c];
						}
					}
				} else {
					for (int c = left; c < right; c++) {
						if (map[bottom - 1][c] < 2) {
							map[top][c] = map[bottom - 1][c] + 2;
						} else {
							map[top][c] = map[bottom - 1][c] - 2;
						}
					}
				}
				col = true;
				break;
			case 'U':
				bottom += (bottom - top);
				if (col) {
					for (int r = (top + bottom) / 2; r < bottom; r++) {
						for (int c = left; c < right; c++) {
							map[r][c] = map[r - (bottom - top) / 2][c];
						}
					}
				} else {
					for (int c = left; c < right; c++) {
						if (map[top][c] < 2) {
							map[bottom - 1][c] = map[top][c] + 2;
						} else {
							map[bottom - 1][c] = map[top][c] - 2;
						}
					}
				}
				col = true;
				break;
			}

			if (row && col) {
				break;
			}
		}

		int w = right - left, h = bottom - top;
		for (int sr = 0; sr < n / h; sr++) {
			for (int sc = 0; sc < n / w; sc++) {
				for (int r = sr * h; r < sr * h + h; r++) {
					for (int c = sc * w; c < sc * w + w; c++) {
						map[r][c] = map[top + r - sr * h][left + c - sc * w];
					}
				}
			}
		}

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				output.append(map[r][c]).append(" ");
			}
			output.append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		k = Integer.parseInt(input.readLine());
		n = (int) Math.pow(2, k);
		st = new StringTokenizer(input.readLine());
		map = new int[n][n];
		orders = new char[n];
		for (int i = 0; i < 2 * k; i++) {
			orders[i] = st.nextToken().charAt(0);
		}
		hollPosition = Integer.parseInt(input.readLine());

		solution();

		System.out.print(output);
	}

}
