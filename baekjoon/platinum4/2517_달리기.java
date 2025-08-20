import java.util.*;
import java.io.*;

class SegmentTree {
    int[] tree;
    int treeSize;

    SegmentTree(int n) {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        tree = new int[treeSize];
    }

    void update(int node, int start, int end, int target) {
        if (target < start || end < target) {
            return;
        }
        tree[node] += 1;

        if (start != end) {
            update(node * 2, start, (start + end) / 2, target);
            update(node * 2 + 1, (start + end) / 2 + 1, end, target);
        }
    }

    int sum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        return sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] players;
    static PriorityQueue<Integer> pq;

    static void solution() {
        int[] sorted = new int[N];
        int idx = 0;
        while (!pq.isEmpty()) {
            sorted[idx++] = pq.poll();
        }
        SegmentTree tree = new SegmentTree(N);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(sorted[i], i);
        }

        for (int stat : players) {
            tree.update(1, 0, N - 1, map.get(stat));
            output.append(tree.sum(1, 0, N - 1, 0, map.get(stat))).append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        players = new int[N];
        pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            players[i] = Integer.parseInt(st.nextToken());
            pq.offer(players[i]);
        }

        solution();

        System.out.print(output);
    }
}
