import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 14.
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZCWNHD6B-8DFASB&contestProbId=AV14eWb6AAkCFAYD&probBoxId=AZFIwxLqKmIDFAQW&type=PROBLEM&problemBoxTitle=0812%EC%A3%BC&problemBoxCnt=4
 * @timecomplex O(n)
 * @performance 19,152 kb, 106 ms
 * @category #스택
 * @note
 */

class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;
	static Map<Character, Character> mapper;

	static int solution(int num, char[] str) {
		int answer = 1;

		ArrayDeque<Character> stack = new ArrayDeque<>();
		label: for (char c : str) {
			switch (c) {
			case '(':
			case '[':
			case '{':
			case '<':
				stack.offer(c);
				break;
			default:
				if (stack.size() == 0 || stack.getLast() != mapper.get(c)) {
					answer = 0;
					break label;
				} else {
					stack.pollLast();
				}
				break;
			}

		}
		if (stack.size() != 0) {
			answer = 0;
		}

		return answer;
	}

	public static void main(String args[]) throws Exception {
		mapper = new HashMap<>();
		mapper.put(')', '(');
		mapper.put(']', '[');
		mapper.put('}', '{');
		mapper.put('>', '<');

		for (int tc = 1; tc <= 10; tc++) {
			int num = Integer.parseInt(input.readLine());
			String str = input.readLine();

			output.append("#").append(tc).append(" ").append(solution(num, str.toCharArray())).append("\n");
		}

		System.out.print(output);
	}
}
