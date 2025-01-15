import java.util.*;
import java.io.*;

class Union {
    int[] parents;

    Union(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
    }

    int findRoot(int node) {
        if (parents[node] < 0) {
            return node;
        }

        return parents[node] = findRoot(parents[node]);
    }

    boolean union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (aRoot == bRoot) {
            return false;
        }

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static boolean[][] relations;
    static int[] route;

    static boolean solution() {
        Union union = new Union(N);

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (relations[r][c]) {
                    union.union(r, c);
                }
            }
        }

        int prevRoot = union.findRoot(route[0]);
        for (int i = 1; i < M; i++) {
            if (prevRoot != union.findRoot(route[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());
        M = Integer.parseInt(input.readLine());
        relations = new boolean[N][N];
        route = new int[M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(input.readLine());
            for (int c = 0; c < N; c++) {
                int n = Integer.parseInt(st.nextToken());
                relations[r][c] = n != 0;
            }
        }
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        System.out.println(solution() ? "YES" : "NO");
    }
}
