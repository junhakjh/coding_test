import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static String[] names;
	static Map<String, Integer> inDegrees;
	static Map<String, List<String>> relations;
	static Map<String, Queue<String>> childrens;

	static void solution() {
		Queue<String> q = new ArrayDeque<>();
		Queue<String> roots = new PriorityQueue<>();

		for (String name : names) {
			if (inDegrees.get(name) == 0) {
				q.offer(name);
				roots.offer(name);
			}
		}

		while (!q.isEmpty()) {
			String parent = q.poll();
			for (String child : relations.get(parent)) {
				inDegrees.put(child, inDegrees.get(child) - 1);
				if (inDegrees.get(child) == 0) {
					q.offer(child);
					childrens.get(parent).offer(child);
				}
			}
		}
		output.append(roots.size()).append("\n");
		while (!roots.isEmpty()) {
			output.append(roots.poll()).append(" ");
		}
		output.append("\n");
		for (String parent : names) {
			output.append(parent).append(" ").append(childrens.get(parent).size());
			while (!childrens.get(parent).isEmpty()) {
				output.append(" ").append(childrens.get(parent).poll());
			}
			output.append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		st = new StringTokenizer(input.readLine());
		names = new String[N];
		inDegrees = new HashMap<>();
		relations = new HashMap<>();
		childrens = new HashMap<>();
		for (int i = 0; i < N; i++) {
			names[i] = st.nextToken();
			inDegrees.put(names[i], 0);
			relations.put(names[i], new ArrayList<>());
			childrens.put(names[i], new PriorityQueue<>());
		}
		Arrays.sort(names);
		M = Integer.parseInt(input.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(input.readLine());
			String a = st.nextToken(), b = st.nextToken();
			inDegrees.put(a, inDegrees.get(a) + 1);
			relations.get(b).add(a);
		}

		solution();

		System.out.println(output);
	}
}
