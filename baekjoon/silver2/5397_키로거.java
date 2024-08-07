import java.util.*;
import java.io.*;

class Node {
    Node prev;
    Node next;
    Character value;

    Node() {
        this.prev = null;
        this.next = null;
        this.value = null;
    }

    Node(Node prev, Node next, char value) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }
}

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();

    static void solution(String str) {
        Node cur = new Node();
        Node root = cur;

        for (char c : str.toCharArray()) {
            switch (c) {
                case '<':
                    if (cur.prev != null) {
                        cur = cur.prev;
                    }
                    break;
                case '>':
                    if (cur.next != null) {
                        cur = cur.next;
                    }
                    break;
                case '-':
                    if (cur.prev == null) {
                        break;
                    } else {
                        if (cur.next == null) {
                            cur = cur.prev;
                            cur.next = null;
                        } else {
                            cur.prev.next = cur.next;
                            cur.next.prev = cur.prev;
                            cur = cur.prev;
                        }
                    }
                    break;
                default:
                    if (cur.next == null) {
                        cur.next = new Node(cur, null, c);
                    } else {
                        Node node = new Node(cur, cur.next, c);
                        cur.next.prev = node;
                        cur.next = node;
                    }
                    cur = cur.next;
                    break;
            }
        }
        root = root.next;
        while (root != null) {
            output.append(root.value);
            root = root.next;
        }
        output.append("\n");
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(input.readLine());
        for (int tc = 0; tc < t; tc++) {
            solution(input.readLine());
        }

        System.out.println(output);
    }
}
