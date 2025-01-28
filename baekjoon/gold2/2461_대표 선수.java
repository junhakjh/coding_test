import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[][] abilities;

    static int solution() {
        int answer = Integer.MAX_VALUE;

        int max = 0;
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int r = 0; r < N; r++) {
            minHeap.offer(new int[]{0, r, abilities[r][0]});
            max = Math.max(max, abilities[r][0]);
        }

        while (!minHeap.isEmpty()) {
            int[] item = minHeap.poll();
            int idx = item[0], r = item[1], ability = item[2];
            answer = Math.min(answer, max - ability);

            if (idx + 1 == M) {
                break;
            }
            minHeap.offer(new int[]{idx + 1, r, abilities[r][idx + 1]});
            max = Math.max(max, abilities[r][idx + 1]);
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        abilities = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < M; c++) {
                abilities[r][c] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(abilities[r]);
        }

        System.out.println(solution());
    }
}
