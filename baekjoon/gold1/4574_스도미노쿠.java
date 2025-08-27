import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static Set<Integer> dominoSet;
    static int[][] map;
    static boolean answerFlag;

    static void insert(int r1, int c1, int r2, int c2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            set1.add(i);
            set2.add(i);
        }
        for (int c = 0; c < 9; c++) {
            set1.remove(map[r1][c]);
        }
        for (int r = 0; r < 9; r++) {
            set1.remove(map[r][c1]);
        }
        for (int r = r1 - (r1 % 3); r < r1 - (r1 % 3) + 3; r++) {
            for (int c = c1 - (c1 % 3); c < c1 - (c1 % 3) + 3; c++) {
                set1.remove(map[r][c]);
            }
        }
        for (int c = 0; c < 9; c++) {
            set2.remove(map[r2][c]);
        }
        for (int r = 0; r < 9; r++) {
            set2.remove(map[r][c2]);
        }
        for (int r = r2 - (r2 % 3); r < r2 - (r2 % 3) + 3; r++) {
            for (int c = c2 - (c2 % 3); c < c2 - (c2 % 3) + 3; c++) {
                set2.remove(map[r][c]);
            }
        }
        for (int a : set1) {
            for (int b : set2) {
                if (a == b) {
                    continue;
                }
                int num = 10 * Math.min(a, b) + Math.max(a, b);
                if (!dominoSet.contains(num)) {
                    continue;
                }
                map[r1][c1] = a;
                map[r2][c2] = b;
                dominoSet.remove(num);
                if (dominoSet.size() == 0) {
                    answerFlag = true;
                    return;
                }
                dfs(r1, c1);
                if (answerFlag) {
                    return;
                }
                dominoSet.add(num);
                map[r1][c1] = 0;
                map[r2][c2] = 0;
            }
        }
    }

    static void dfs(int r, int c) {
        for (int nr = r; nr < 9; nr++) {
            for (int nc = 0; nc < 9; nc++) {
                if (nr == r && nc < c) {
                    continue;
                }
                if (map[nr][nc] == 0) {
                    if (nr + 1 < 9 && map[nr + 1][nc] == 0) {
                        insert(nr, nc, nr + 1, nc);
                        if (answerFlag) {
                            return;
                        }
                    }
                    if (nc + 1 < 9 && map[nr][nc + 1] == 0) {
                        insert(nr, nc, nr, nc + 1);
                        if (answerFlag) {
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }

    static void solution(int tc) {
        dfs(0, 0);

        output.append("Puzzle ").append(tc).append("\n");
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                output.append(map[r][c]);
            }
            output.append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        int tc = 1;

        while (true) {
            st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }
            dominoSet = new HashSet<>();
            map = new int[9][9];
            answerFlag = false;
            for (int a = 1; a < 10; a++) {
                for (int b = a + 1; b < 10; b++) {
                    dominoSet.add(10 * a + b);
                }
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                int a = Integer.parseInt(st.nextToken());
                String aPos = st.nextToken();
                int b = Integer.parseInt(st.nextToken());
                String bPos = st.nextToken();
                dominoSet.remove(10 * Math.min(a, b) + Math.max(a, b));
                int ar = aPos.charAt(0) - 65, ac = aPos.charAt(1) - 49;
                int br = bPos.charAt(0) - 65, bc = bPos.charAt(1) - 49;
                map[ar][ac] = a;
                map[br][bc] = b;
            }
            st = new StringTokenizer(input.readLine());
            for (int i = 1; i < 10; i++) {
                String pos = st.nextToken();
                int r = pos.charAt(0) - 65, c = pos.charAt(1) - 49;
                map[r][c] = i;
            }

            solution(tc++);
        }

        System.out.print(output);
    }
}
