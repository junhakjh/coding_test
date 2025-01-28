import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, K;
    static int[] arr;

    static int solution() {
        int answer = 0;

        int sum = 0, unit = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (arr[i] % 2 == 0) {
                unit++;
                sum++;
                answer = Math.max(answer, sum);
            } else {
                q.offer(unit);
                unit = 0;
                if (K == 0 && !q.isEmpty()) {
                    sum -= q.poll();
                } else {
                    K--;
                }
            }
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}
