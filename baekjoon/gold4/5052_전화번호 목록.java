import java.io.*;
import java.util.*;

class Node {
	Map<Character, Node> children;
	boolean isEnd;

	Node() {
		children = new HashMap<>();
		isEnd = false;
	}
}

class Trie {
	Node root;

	Trie() {
		root = new Node();
	}

	void insert(String str) {
		Node node = root;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			node.children.putIfAbsent(c, new Node());
			node.isEnd = false;
			node = node.children.get(c);
		}
		
		if(node.children.size() == 0) {
			node.isEnd = true;			
		}
	}

	boolean isDuplicated(String str) {
		Node node = root;

		boolean check = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			Node child = node.children.get(c);
			if (child == null) {
				check = true;
				break;
			}
			node = child;
		}

		if (!check && !node.isEnd) {
			return true;
		}
		return false;
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static Trie trie;
	static String[] arr;

	static boolean solution() {
		for (int i = 0; i < n; i++) {
			if (trie.isDuplicated(arr[i])) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());

		for (int tc = 0; tc < T; tc++) {
			trie = new Trie();
			n = Integer.parseInt(input.readLine());
			arr = new String[n];
			for (int i = 0; i < n; i++) {
				String str = input.readLine();
				arr[i] = str;
				trie.insert(str);
			}

			System.out.println(solution() ? "YES" : "NO");
		}

	}
}
