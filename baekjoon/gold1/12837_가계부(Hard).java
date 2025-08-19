import java.util.*;
import java.io.*;

class SegmentTree {
    int treeSize;
    long[] tree;

    SegmentTree(int n) {
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
    }

    void update(int node, int start, int end, int target, int diff) {
        if (end < target || start > target) {
            return;
        }
        tree[node] += diff;

        if (start != end) {
            update(node * 2, start, (start + end) / 2, target, diff);
            update(node * 2 + 1, (start + end) / 2 + 1, end, target, diff);
        }
    }

    long sum(int node, int start, int end, int left, int right) {
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

    static int N, Q;
    static int[][] queries;

    static void solution() {
        SegmentTree tree = new SegmentTree(N);
        for (int[] query : queries) {
            if (query[0] == 1) {
                tree.update(1, 1, N, query[1], query[2]);
            }
            if (query[0] == 2) {
                output.append(tree.sum(1, 1, N, query[1], query[2])).append("\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        queries = new int[Q][3];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(input.readLine());
            queries[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        solution();

        System.out.print(output);
    }
}
