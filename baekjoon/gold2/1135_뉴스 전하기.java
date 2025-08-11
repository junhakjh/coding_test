import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static Map<Integer, List<Integer>> parentsMap;

    static int dfs(int curI) {
        if (parentsMap.get(curI).size() == 0) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        for (int nextI : parentsMap.get(curI)) {
            list.add(dfs(nextI));
        }
        list.sort((a, b) -> Integer.compare(b, a));
        int result = 0, plus = 1;
        for (int num : list) {
            result = Math.max(result, num + plus++);
        }

        return result;
    }

    static int solution() {
        return dfs(0);
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        parentsMap = new HashMap<>();
        st = new StringTokenizer(input.readLine());
        for (int i = -1; i < N; i++) {
            parentsMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            parentsMap.get(parent).add(i);
        }

        System.out.println(solution());
    }
}
