import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static String str;
	static int length;

	static void dfs(int pivot, int left, boolean[] visited) {
		int nextPivot = -1;
		int min = Integer.MAX_VALUE;
		for (int i = pivot + 1; i < length; i++) {
			if (!visited[i] && min > str.charAt(i)) {
				min = str.charAt(i);
				nextPivot = i;
			}
		}
		if (nextPivot != -1) {
			visited[nextPivot] = true;
			for (int i = 0; i < length; i++) {
				if (visited[i]) {
					output.append(str.charAt(i));
				}
			}
			output.append("\n");
			dfs(nextPivot, pivot + 1, visited);
		}

		nextPivot = -1;
		min = Integer.MAX_VALUE;
		for (int i = left; i < pivot; i++) {
			if (!visited[i] && min > str.charAt(i)) {
				min = str.charAt(i);
				nextPivot = i;
			}
		}
		if (nextPivot != -1) {
			visited[nextPivot] = true;
			for (int i = 0; i < length; i++) {
				if (visited[i]) {
					output.append(str.charAt(i));
				}
			}
			output.append("\n");
			dfs(nextPivot, left, visited);
		}
	}

	public static void main(String[] args) throws Exception {
		str = input.readLine();
		length = str.length();
		boolean[] visited = new boolean[length];
		int startI = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < length; i++) {
			if (min > str.charAt(i)) {
				min = str.charAt(i);
				startI = i;
			}
		}
		visited[startI] = true;

		output.append(str.charAt(startI));
		output.append("\n");
		dfs(startI, 0, visited);

		System.out.print(output);
	}
}
