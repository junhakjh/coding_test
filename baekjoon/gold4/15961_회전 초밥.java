import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 9. 3.
 * @link https://www.acmicpc.net/problem/15961
 * @timecomplex O(n)
 * @performance 170,948 kb, 520 ms
 * @category #슬라이딩 윈도우
 * @note
 */

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n, d, k, c;
	static int[] dishList;
	static int[] sushiList;

	static int solution() {
		int answer = 1;

		int num = 1;
		for (int i = 0; i < k; i++) {
			sushiList[dishList[i]] += 1;
			if (sushiList[dishList[i]] == 1) {
				num++;
				answer = Math.max(answer, num);
			}
		}
		for(int i = k; i < n + k; i++) {
			sushiList[dishList[i - k]] -= 1;
			if(sushiList[dishList[i - k]] == 0) {
				num--;
			}
			sushiList[dishList[i % n]] += 1;
			if(sushiList[dishList[i % n]] == 1) {
				num++;
				answer = Math.max(answer, num);
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dishList = new int[n];
		sushiList = new int[d + 1];
		sushiList[c] = 1;
		for (int i = 0; i < n; i++) {
			dishList[i] = Integer.parseInt(input.readLine());
		}

		System.out.println(solution());
	}
}
