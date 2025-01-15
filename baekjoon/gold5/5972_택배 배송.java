import java.util.*;
import java.io.*;

class Node {
    int num;
    int value;
    Node(int num, int value) {
        this.num = num;
        this.value = value;
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static Map<Integer, List<Node>> map;

    static int solution() {
        int answer = 0;
        int[] dp = new int[N + 1];
        for(int i = 2; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        Queue<Node> q = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));
        q.offer(new Node(1, 0));
        while(!q.isEmpty()) {
            Node node = q.poll();
            for(Node next: map.get(node.num)) {
                if(dp[next.num] > dp[node.num] + next.value) {
                    dp[next.num] = dp[node.num] + next.value;
                    q.offer(next);
                }
            }
        }
        answer = dp[N];

        return answer;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map.get(a).add(new Node(b, c));
            map.get(b).add(new Node(a, c));
        }

        System.out.println(solution());
    }
}
