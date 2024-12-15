import java.util.*;
import java.io.*;

class TrieNode {
	Map<String, TrieNode> children;

	TrieNode() {
		children = new HashMap<>();
	}

	void insert(String[] words) {
		TrieNode node = this;
		for (String word : words) {
			node.children.putIfAbsent(word, new TrieNode());
			node = node.children.get(word);
		}
	}

	void search(StringBuilder output, int depth, String value, TrieNode node) {
		for (int i = 0; i < depth; i++) {
			output.append("--");
		}
		output.append(value).append("\n");
		List<String> keyList = new ArrayList<>(node.children.keySet());
		Collections.sort(keyList);
		for (String key : keyList) {
			search(output, depth + 1, key, node.children.get(key));
		}
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static TrieNode trie;

	static void solution() {
		List<String> keyList = new ArrayList<>(trie.children.keySet());
		Collections.sort(keyList);
		for (String key : keyList) {
			trie.search(output, 0, key, trie.children.get(key));
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		trie = new TrieNode();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			int K = Integer.parseInt(st.nextToken());
			String[] words = new String[K];
			for (int j = 0; j < K; j++) {
				words[j] = st.nextToken();
			}
			trie.insert(words);
		}

		solution();

		System.out.println(output);
	}
}
