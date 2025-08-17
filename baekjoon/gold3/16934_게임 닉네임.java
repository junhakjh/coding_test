import java.util.*;
import java.io.*;

class Node {
    boolean isFinal;
    Map<Character, Node> children;

    Node() {
        this.isFinal = false;
        this.children = new HashMap<>();
    }
}

class Trie {
    Node root;

    Trie() {
        this.root = new Node();
    }

    int add(String str) {
        int lastIdx = 0;
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (node.children.containsKey(c)) {
                lastIdx++;
            } else {
                node.children.put(c, new Node());
            }
            node = node.children.get(c);
        }
        if (lastIdx == str.length() && node.isFinal) {
            lastIdx = -1;
        }
        node.isFinal = true;
        return lastIdx;
    }
}

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static String[] arr;

    static void solution() {
        Map<String, Integer> map = new HashMap<>();
        Trie trie = new Trie();

        for (String str : arr) {
            map.putIfAbsent(str, 1);
            int lastIdx = trie.add(str);
            if (lastIdx == -1) {
                map.put(str, map.get(str) + 1);
                output.append(str).append(map.get(str));
            } else {
                for (int i = 0; i <= Math.min(lastIdx, str.length() - 1); i++) {
                    output.append(str.charAt(i));
                }
            }
            output.append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new StringTokenizer(input.readLine()).nextToken();
        }

        solution();

        System.out.print(output);
    }
}
