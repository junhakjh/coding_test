import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static String str, bomb;
    static boolean[] isLeft;

    static void dfs() {

    }

    static void solution() {
        int k = 0;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        ArrayDeque<Integer> kStack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            stack.offerLast(str.charAt(i));
            if (str.charAt(i) == bomb.charAt(0) && k != 0) {
                kStack.offerLast(k);
                k = 0;
            }
            if (str.charAt(i) == bomb.charAt(k)) {
                if (++k == bomb.length()) {
                    while (k-- > 0) {
                        stack.pollLast();
                    }
                    k = 0;
                    if (!kStack.isEmpty()) {
                        k = kStack.pollLast();
                    }
                }
            } else {
                k = 0;
                kStack.clear();
            }
        }

        if (stack.isEmpty()) {
            output.append("FRULA");
        } else {
            while (!stack.isEmpty()) {
                output.append(stack.pollFirst());
            }
        }
    }


    public static void main(String[] args) throws Exception {
        str = input.readLine();
        bomb = input.readLine();
        isLeft = new boolean[str.length()];

        solution();

        System.out.println(output);
    }
}
