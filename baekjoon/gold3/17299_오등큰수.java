import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] arr;
    static Map<Integer, Integer> numMap;

    static void solution() {
        int[] answerArr = new int[N];
        answerArr[N - 1] = -1;
        label:
        for (int i = N - 2; i >= 0; i--) {
            if (numMap.get(arr[i + 1]) > numMap.get(arr[i])) {
                answerArr[i] = i + 1;
                continue;
            }
            int j = answerArr[i + 1];
            while (j > 0) {
                if (numMap.get(arr[j]) > numMap.get(arr[i])) {
                    answerArr[i] = j;
                    continue label;
                }
                j = answerArr[j];
            }
            answerArr[i] = -1;
        }
        for (int i : answerArr) {
            output.append(i == -1 ? -1 : arr[i]).append(" ");
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        numMap = new HashMap<>();
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            numMap.putIfAbsent(num, 0);
            numMap.put(num, numMap.get(num) + 1);
        }

        solution();

        System.out.println(output);
    }
}
