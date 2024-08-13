import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 13.
 * @link https://www.acmicpc.net/problem/15649
 * @timecomplex O(n!)
 * @performance 21688 kb, 268 ms
 * @category #백트래킹
 * @note
 */

class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	static int num;
	static int len;
	
	static void permutation(int depth, int[] selected, boolean[] visited) {
		if (depth == len) {
			for(int i = 0; i < len; i++) {
				output.append(selected[i]).append(" ");
			}
			output.append("\n");
			return;
		}
		
		for(int i = 0; i < num; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth] = i + 1;
				permutation(depth + 1, selected, visited);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		st = new StringTokenizer(input.readLine());
		num = Integer.parseInt(st.nextToken());
		len = Integer.parseInt(st.nextToken());
		
		permutation(0, new int[len], new boolean[num]);
		
		System.out.print(output);
	}
}
