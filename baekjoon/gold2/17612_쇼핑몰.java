import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, K;
    static int[][] infos;

    static long solution() {
        long answer = 0;

        Queue<int[]> inPq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[2], b[2]);
            }
            return Integer.compare(a[1], b[1]);
        });
        Queue<long[]> outPq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return (-1) * Long.compare(a[2], b[2]);
            }
            return Long.compare(a[1], b[1]);
        });

        for (int i = 0; i < Math.min(N, K); i++) {
            inPq.offer(new int[]{infos[i][0], infos[i][1], i});
        }
        int i = K;
        while (!inPq.isEmpty()) {
            int[] info = inPq.poll();
            outPq.offer(new long[]{info[0], info[1], info[2]});
            if (i < N) {
                inPq.offer(new int[]{infos[i][0], infos[i][1] + info[1], info[2]});
                i++;
            }
        }

        i = 1;
        while (!outPq.isEmpty()) {
            long[] info = outPq.poll();
            answer += i++ * info[0];
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        infos = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            infos[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        System.out.println(solution());
    }
}
