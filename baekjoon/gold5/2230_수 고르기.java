import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] arr;

    static int solution() {
        int answer = Integer.MAX_VALUE;

        Arrays.sort(arr);
        int l = 0, r = 0;
        while (l <= r && r < N) {
            int diff = arr[r] - arr[l];
            if (diff >= M) {
                answer = Math.min(answer, diff);
                l++;
            } else {
                r++;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(new StringTokenizer(input.readLine()).nextToken());
        }

        System.out.print(solution());
    }
}
