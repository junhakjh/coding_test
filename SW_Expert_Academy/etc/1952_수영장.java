import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 4.
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
 * @timecomplex
 * @performance 18,628 kb, 115 ms
 * @category #dp
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int[] costs;
	static int[] months;

	static int answer;

	static int solution() {
		int[] minCosts = new int[13];
		for(int i = 1; i <= 12; i++) {
			minCosts[i] = minCosts[i - 1] + costs[0] * months[i];
			minCosts[i] = Math.min(minCosts[i], minCosts[i - 1] + costs[1]);
			if(i >= 3) {
				minCosts[i] = Math.min(minCosts[i], minCosts[i - 3] + costs[2]);				
			}
		}
		
		return Math.min(minCosts[12], costs[3]);
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			costs = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			st = new StringTokenizer(input.readLine());
			months = new int[13];
			for (int i = 1; i <= 12; i++) {
				months[i] = Integer.parseInt(st.nextToken());
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
