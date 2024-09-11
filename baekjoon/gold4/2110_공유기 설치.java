import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, c;
	static List<Integer> positions;

	static int solution() {
		int answer = 1;

		int startN = positions.get(0);
		int itvL = 1;
		int itvR = (positions.get(n - 1) - positions.get(0)) / (c - 1) + 1;
		while(itvL < itvR) {
			int itv = (itvL + itvR) / 2;
			int prevIdx = 0, minItv = itv;
			for(int i = 0; i < c - 2; i++) {
				int idx = Collections.binarySearch(positions, positions.get(prevIdx) + itv);
				if(idx < 0) {
					idx = (-1) * idx - 1;
				}
				if(idx >= n) {
					idx = n - 1;
				}
				minItv = Math.min(minItv, positions.get(idx) - positions.get(prevIdx));
				prevIdx = idx;
			}
			minItv = Math.min(minItv, positions.get(n - 1) - positions.get(prevIdx));
			if(minItv != itv) {
				itvR = (itvL + itvR) / 2;
			} else {
				itvL = (itvL + itvR) / 2 + 1;
				answer = Math.max(answer, minItv);
			}
		}
		
		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		positions = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			positions.add(i, Integer.parseInt(input.readLine()));
		}
		Collections.sort(positions);

		System.out.println(solution());
		
	}
}
