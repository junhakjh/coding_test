import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static Map<Integer, List<Integer>> map;

    static void findNextIntersection(int startKey, int curKey, int[] track, boolean[] isCircular) {
        if (map.get(curKey).size() == 1) {
            return;
        }

        for (int nextKey : map.get(curKey)) {
            if (track[nextKey] == 0) {
                track[nextKey] = curKey;
                findNextIntersection(startKey, nextKey, track, isCircular);
            }
            if (nextKey == startKey && track[curKey] != startKey) {
                isCircular[startKey] = true;
                int key = curKey;
                while (key != startKey) {
                    isCircular[key] = true;
                    key = track[key];
                }
            }
        }
    }

    static void solution() {
        boolean[] isCircular = new boolean[N + 1];
        boolean flag = false;
        for (int key : map.keySet()) {
            if (map.get(key).size() > 2 && !isCircular[key]) {
                int[] track = new int[N + 1];
                track[key] = -1;
                findNextIntersection(key, key, track, isCircular);
                flag = true;
            }
        }
        if (!flag) {
            for (int i = 1; i <= N; i++) {
                isCircular[i] = true;
            }
        }

        label:
        for (int startKey = 1; startKey <= N; startKey++) {
            if (isCircular[startKey]) {
                output.append(0).append(" ");
                continue;
            }
            boolean[] visited = new boolean[N + 1];
            visited[startKey] = true;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{0, startKey});
            while (!q.isEmpty()) {
                int[] item = q.poll();
                int dist = item[0], curKey = item[1];
                for (int nextKey : map.get(curKey)) {
                    if (!visited[nextKey]) {
                        if (isCircular[nextKey]) {
                            output.append(dist + 1).append(" ");
                            continue label;
                        }
                        visited[nextKey] = true;
                        q.offer(new int[]{dist + 1, nextKey});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            map.get(b).add(a);
        }

        solution();

        System.out.println(output);
    }
}
