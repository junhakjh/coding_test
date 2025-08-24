import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static boolean[][] friendship;
    static int[] friends;

    static int solution() {
        int answer = Integer.MAX_VALUE;

        for (int a = 0; a < N - 2; a++) {
            for (int b = a + 1; b < N - 1; b++) {
                if (!friendship[a][b]) {
                    continue;
                }
                for (int c = b + 1; c < N; c++) {
                    if (!friendship[b][c] || !friendship[c][a]) {
                        continue;
                    }
                    answer = Math.min(answer, friends[a] + friends[b] + friends[c] - 6);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        friendship = new boolean[N][N];
        friends = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
            friendship[a][b] = true;
            friendship[b][a] = true;
            friends[a]++;
            friends[b]++;
        }

        System.out.println(solution());
    }
}
