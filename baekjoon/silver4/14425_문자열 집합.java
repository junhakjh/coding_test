import java.util.*;
import java.io.*;

class TrieNode {
	TrieNode[] children;
	boolean eow;

	TrieNode() {
		children = new TrieNode[26];
		eow = false;
	}

	void insert(String word) {
		TrieNode node = this;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.children[c - 'a'] == null) {
				node.children[c - 'a'] = new TrieNode();
			}
			node = node.children[c - 'a'];
		}
		node.eow = true;
	}

	boolean search(String word) {
		TrieNode node = this;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.children[c - 'a'] == null) {
				return false;
			}
			node = node.children[c - 'a'];
		}
		return node.eow;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static TrieNode trie;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trie = new TrieNode();

		for (int i = 0; i < N; i++) {
			trie.insert(input.readLine());
		}
		int answer = 0;
		for (int i = 0; i < M; i++) {
			if (trie.search(input.readLine())) {
				answer++;
			}
		}

		System.out.println(answer);
	}
}
