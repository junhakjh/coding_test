```java
import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 16.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV14uWl6AF0CFAYD&probBoxId=AZFIwxLqKmIDFAQW&type=PROBLEM&problemBoxTitle=0812%EC%A3%BC&problemBoxCnt=7
 * @timecomplex O(?)
 * @performance 18,416 kb, 107 ms
 * @category #완전탐색
 * @note
 */

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int[] nums = new int[8];

	static String solution() {
		StringBuilder answer = new StringBuilder();

		int i = 0, minus = 1;
		while (true) {
			nums[i] -= minus++;
			if (nums[i++] <= 0) {
				nums[i - 1] = 0;
				if (i >= 8) {
					i = 0;
				}
				break;
			}
			if (i >= 8) {
				i = 0;
			}
			if (minus > 5) {
				minus = 1;
			}
		}

		for (int j = 0; j < 8; j++) {
			answer.append(nums[i++]).append(" ");
			if (i >= 8) {
				i = 0;
			}
		}

		return answer.toString();
	}

	public static void main(String[] args) throws Exception {
		for (int tc = 1; tc <= 2; tc++) {
			input.readLine();
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < 8; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			output.append("#").append(tc).append(" ").append(solution()).append("\n");
		}

		System.out.print(output);
	}
}
```
