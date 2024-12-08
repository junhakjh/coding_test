import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static char[] word;
	static int len;

	static void dfs(int depth, boolean[] visited, char[] result) {
		if (depth == len) {
			for (char c : result) {
				output.append(c);
			}
			output.append("\n");
			return;
		}

		for (int i = 0; i < len; i++) {
			if (!visited[i]) {
				if (i > 0 && word[i] == word[i - 1] && !visited[i - 1]) {
					continue;
				}
				visited[i] = true;
				result[depth] = word[i];
				dfs(depth + 1, visited, result);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(input.readLine());
		for (int tc = 0; tc < N; tc++) {
			String str = input.readLine();
			word = str.toCharArray();
			Arrays.sort(word);
			len = word.length;
			dfs(0, new boolean[len], new char[len]);
		}
		System.out.print(output);
	}
}
