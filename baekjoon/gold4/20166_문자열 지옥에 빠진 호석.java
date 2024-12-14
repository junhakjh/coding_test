import java.util.*;
import java.io.*;

class TrieNode {
	Map<Character, TrieNode> children;
	int cnt;

	TrieNode() {
		children = new HashMap<>();
		cnt = 0;
	}

	void insert(String word) {
		TrieNode node = this;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			node.children.putIfAbsent(c, new TrieNode());
			node = node.children.get(c);
		}
		node.cnt += 1;
	}

	int count(String word) {
		TrieNode node = this;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			node = node.children.get(c);
			if (node == null) {
				return 0;
			}
		}
		return node.cnt;
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 }, dc = { 1, 1, 0, -1, -1, -1, 0, 1 };

	static int N, M, K;
	static char[][] map;
	static String[] words;
	static TrieNode trie;
	static int cnt;

	static void generateTrie(int r, int c, int depth, char[] word) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(word[i]);
		}
		trie.insert(sb.toString());
		if (depth == 5) {
			return;
		}

		for (int i = 0; i < 8; i++) {
			int nr = (r + dr[i]) % N, nc = (c + dc[i]) % M;
			if (nr < 0) {
				nr += N;
			}
			if (nc < 0) {
				nc += M;
			}
			word[depth] = map[nr][nc];
			generateTrie(nr, nc, depth + 1, word);
		}
	}

	static void solution() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				char[] word = new char[5];
				word[0] = map[r][c];
				generateTrie(r, c, 1, word);
			}
		}
		for (String word : words) {
			output.append(trie.count(word)).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		words = new String[K];
		trie = new TrieNode();
		for (int r = 0; r < N; r++) {
			String str = input.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		for (int i = 0; i < K; i++) {
			words[i] = input.readLine();
		}

		solution();

		System.out.println(output);
	}
}
