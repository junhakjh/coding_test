import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, S;
    static int[] arr;

    static int solution() {
        int answer = Integer.MAX_VALUE;

        int sum = arr[0];
        int l = 0, r = 0;

        while (r < N) {
            if (answer == 0) {
                return answer;
            }
            if (sum >= S) {
                answer = Math.min(answer, r - l + 1);
                sum -= arr[l++];
            } else {
                if (r == N - 1) {
                    break;
                }
                sum += arr[++r];
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(solution());
    }
}
