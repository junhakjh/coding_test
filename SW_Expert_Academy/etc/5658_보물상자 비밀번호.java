import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 6.
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo
 * @timecomplex
 * @performance 28,188 kb, 261 ms
 * @category #우선순위큐
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, k;

	static int solution(String str) {
		int answer = 0;

		int unit = n / 4;
		Queue<String> pq = new PriorityQueue<>((s1, s2) -> (-1) * s1.compareTo(s2));
		for (int i = 0; i < unit; i++) {
			for (int j = 0; j < 4; j++) {
				StringBuilder sb = new StringBuilder();
				for (int k = i; k < i + unit; k++) {
					int idx = (j * unit + k) % n;
					sb.append(str.charAt(idx));
				}
				pq.offer(sb.toString());
			}
		}
		
		String result = "";
		while(k-- > 0) {
			String temp = pq.poll();
			if(temp.equals(result)) {
				k++;
				continue;
			}
			result = temp;
		}
		answer = Integer.parseInt(result, 16);

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			output.append("#").append(tc).append(" ").append(solution(input.readLine())).append("\n");
		}

		System.out.print(output);
	}
}
