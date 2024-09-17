import java.util.*;
import java.io.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, k;
	static int[] tab, uses;
	static Map<Integer, ArrayDeque<Integer>> plugsOrder;

	static int solution() {
		int answer = 0;

		label: for (int use : uses) {
			plugsOrder.get(use).poll();
			for (int i = 0; i < n; i++) {
				if (tab[i] == 0 || tab[i] == use) {
					tab[i] = use;
					continue label;
				}
			}
			answer++;
			int prevPlugIdx = -1, prevPlugOrder = -1;
			boolean check = false;
			for (int i = 0; i < n; i++) {
				if (plugsOrder.get(tab[i]).isEmpty()) {
					if (prevPlugIdx != -1) {
						plugsOrder.get(tab[prevPlugIdx]).offerFirst(prevPlugOrder);
					}
					prevPlugIdx = i;
					break;
				}
				int curOrder = plugsOrder.get(tab[i]).poll();
				if (curOrder > prevPlugOrder) {
					if (prevPlugIdx != -1) {
						plugsOrder.get(tab[prevPlugIdx]).offerFirst(prevPlugOrder);
					}
					prevPlugIdx = i;
					prevPlugOrder = curOrder;
				} else {
					plugsOrder.get(tab[i]).offerFirst(curOrder);
				}
			}
			if (prevPlugIdx != -1) {
				plugsOrder.get(tab[prevPlugIdx]).offerFirst(prevPlugOrder);
			}
			tab[prevPlugIdx] = use;
		}


		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		tab = new int[n];
		uses = new int[k];
		plugsOrder = new HashMap<>();
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < k; i++) {
			uses[i] = Integer.parseInt(st.nextToken());
			if (plugsOrder.get(uses[i]) == null) {
				plugsOrder.put(uses[i], new ArrayDeque<>());
			}
			plugsOrder.get(uses[i]).offer(i);
		}

		System.out.println(solution());
	}
}
