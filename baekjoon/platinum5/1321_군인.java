import java.util.*;
import java.io.*;

class SegTree {
    long[] tree;

    SegTree(int n) {
        tree = new long[n * 4];
    }

    long init(int index, int start, int end, int[] arr) {
        if (start == end) {
            return tree[index] = arr[start];
        }

        return tree[index] = init(index * 2, start, (start + end) / 2, arr) + init(index * 2 + 1, (start + end) / 2 + 1, end, arr);
    }

    void change(int index, int start, int end, long target, long diff) {
        if (start > target || end < target) {
            return;
        }
        tree[index] += diff;
        if (start == end) {
            return;
        }
        change(index * 2, start, (start + end) / 2, target, diff);
        change(index * 2 + 1, (start + end) / 2 + 1, end, target, diff);
    }

    int getIndex(int index, int start, int end, long target) {
        if (start == end) {
            return start + 1;
        }
        if (target <= tree[index * 2]) {
            return getIndex(index * 2, start, (start + end) / 2, target);
        } else {
            return getIndex(index * 2 + 1, (start + end) / 2 + 1, end, target - tree[index * 2]);
        }
    }
}

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] units;
    static long[][] orders;

    static void solution() {
        SegTree tree = new SegTree(N);
        tree.init(1, 0, N - 1, units);
        for (long[] order : orders) {
            if (order[0] == 1) {
                tree.change(1, 0, N - 1, order[1] - 1, order[2]);
            } else {
                output.append(tree.getIndex(1, 0, N - 1, order[1])).append("\n");
            }
        }
    }


    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        units = new int[N];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            units[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(input.readLine());
        orders = new long[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int j = 0;
            while (st.hasMoreTokens()) {
                orders[i][j++] = Long.parseLong(st.nextToken());
            }
        }

        solution();

        System.out.print(output);
    }
}
