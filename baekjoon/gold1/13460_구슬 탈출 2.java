import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};

    static class Record {
        char[][] board;
        int num;

        Record(char[][] board, int num) {
            this.board = board;
            this.num = num;
        }
    }

    static int solution(int height, int width, char[][] board) {
        int answer = -1;

        Queue<Record> q = new ArrayDeque<>();
        q.offer(new Record(board, 1));

        while (!q.isEmpty()) {
            Record record = q.poll();

            for (int i = 0; i < 4; i++) {
                int r, c;
                int[] block;
                boolean isGoal = false;
                if (i == 0 || i == 1) {
                    r = 0;
                    c = 0;
                } else if (i == 2) {
                    r = height - 1;
                    c = 0;
                } else {
                    r = 0;
                    c = width - 1;
                }
                block = new int[]{r, c};

                char[][] copyBoard = new char[height][width];
                for (int row = 0; row < height; row++) {
                    copyBoard[row] = record.board[row].clone();
                }

                boolean flag = false;
                boolean isMove = false;
                while (r >= 0 && r < height && c >= 0 && c < width) {
                    if (copyBoard[r][c] == '#') {
                        isGoal = false;
                        block[0] = r;
                        block[1] = c;
                    } else if (copyBoard[r][c] == 'O') {
                        isGoal = true;
                    } else if (copyBoard[r][c] == 'R') {
                        if (isGoal) {
                            answer = record.num;
                        } else {
                            block[0] = block[0] + dr[i];
                            block[1] = block[1] + dc[i];
                            if(block[0] != r || block[1] != c) {
                                copyBoard[block[0]][block[1]] = copyBoard[r][c];
                                copyBoard[r][c] = '.';
                                isMove = true;
                            }
                        }
                    } else if (copyBoard[r][c] == 'B') {
                        if (isGoal) {
                            flag = true;
                            answer = -1;
                            break;
                        } else {
                            block[0] = block[0] + dr[i];
                            block[1] = block[1] + dc[i];
                            if(block[0] != r || block[1] != c) {
                                copyBoard[block[0]][block[1]] = copyBoard[r][c];
                                copyBoard[r][c] = '.';
                                isMove = true;
                            }
                        }
                    }

                    r += dr[i];
                    c += dc[i];
                    if (r < 0 || r >= height || c < 0 || c >= width) {
                        isGoal = false;
                        if (i == 0) {
                            c++;
                            r = 0;
                        } else if (i == 1) {
                            r++;
                            c = 0;
                        } else if (i == 2) {
                            c++;
                            r = height - 1;
                        } else {
                            r++;
                            c = width - 1;
                        }
                    }
                }
                if (!flag) {
                    if (answer != -1) return answer;
                    if (record.num < 10 && isMove) {
                        q.offer(new Record(copyBoard, record.num + 1));
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        int height = Integer.parseInt(st.nextToken()), width = Integer.parseInt(st.nextToken());
        char[][] board = new char[height][width];
        for (int r = 0; r < height; r++) {
            board[r] = input.readLine().toCharArray();
        }
        System.out.println(solution(height, width, board));
    }
}
