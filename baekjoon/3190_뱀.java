import java.io.*;
import java.util.*;

public class Main {
	static final char LEFT = 'L';
	static final char RIGHT = 'D';
	static final int EMPTY = 0;
	static final int APPLE = 1;
	static final int SNAKE = 2;
	static final int[] dx = {1, 0, -1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int k;
	static int l;
	static int[][] board;
	static String[][] infos;
	
	static int solution() {
		int answer = 0;
		int dir = 0;
		int x = 0; int y = 0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{0, 0});
		
		for(String[] info: infos) {
			int time = Integer.parseInt(info[0]);
			
			while(++answer <= time) {
				x = x + dx[dir]; y = y + dy[dir];
				if(x < 0 || x >= n || y < 0 || y >= n) {
					return answer;
				}
				if(board[y][x] == SNAKE) {
					return answer;
				}
				if(board[y][x] == EMPTY) {
					int[] coor = q.poll();
					board[coor[0]][coor[1]] = EMPTY;
				}
				board[y][x] = SNAKE;
				q.offer(new int[] {y, x});
			}
			answer--;
			
			char turn = info[1].charAt(0);
			if(turn == LEFT) {
				dir = (dir + 3) % 4;
			} else {
				dir = (dir + 1) % 4;
			}
		}
		
		while(++answer > 0) {			
			x = x + dx[dir]; y = y + dy[dir];
			if(x < 0 || x >= n || y < 0 || y >= n) {
				return answer;
			}
			if(board[y][x] == SNAKE) {
				return answer;
			}
			if(board[y][x] == EMPTY) {
				int[] coor = q.poll();
				board[coor[0]][coor[1]] = EMPTY;
			}
			board[y][x] = SNAKE;
			q.offer(new int[] {y, x});
		}
		
		return answer;
	}
	
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(input.readLine());
		k = Integer.parseInt(input.readLine());
		
		board = new int[n][n];
		
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(input.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			board[y][x] = 1;
		}
		
		l = Integer.parseInt(input.readLine());
		infos = new String[l][2];
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(input.readLine());
			infos[i][0] = st.nextToken();
			infos[i][1] = st.nextToken();
		}
		
		System.out.println(solution());
	}
}
