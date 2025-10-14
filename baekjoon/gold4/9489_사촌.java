import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, K;
	static int[] arr;

	static void solution() {
		int[] parents = new int[N];
		Arrays.fill(parents, -1);
		int parentIdx = -1, targetIdx = 0;
		for (int i = 1; i < N; i++) {
			if (arr[i] == K) {
				targetIdx = i;
			}
			if (arr[i] - arr[i - 1] != 1) {
				parentIdx++;
			}
			parents[i] = parentIdx;
		}
		if (parents[targetIdx] == -1 || parents[parents[targetIdx]] == -1) {
			output.append("0\n");
			return;
		}
		int parent = arr[parents[targetIdx]], root = arr[parents[parents[targetIdx]]], sum = 0;
		for (int i = 0; i < N; i++) {
			if (parents[i] == -1 || parents[parents[i]] == -1) {
				continue;
			}
			if (arr[parents[i]] != parent && arr[parents[parents[i]]] == root) {
				sum++;
			}
		}
		output.append(sum).append("\n");
	}

	public static void main(String[] args) throws Exception {
		while (true) {
			st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			if (N == 0 && K == 0) {
				break;
			}
			arr = new int[N];
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			solution();
		}

		System.out.println(output);
	}
}
