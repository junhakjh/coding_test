import java.io.*;
import java.util.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int solution(int[] candies) {
		int answer = 0;
		
		int prev = candies[2];
		for(int i = 1; i >= 0; i--) {
			if(candies[i] < prev) {
				prev = candies[i];
				continue;
			}
			answer += candies[i] - (--prev);
		}
		
		return prev <= 0 ? -1 : answer;
	}
	
	public static void main(String[] args) throws Exception {	
		int T = Integer.parseInt(input.readLine());
		
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.readLine());
			int[] candies = new int[3];
			for(int i = 0; i < 3; i++) {
				candies[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(tc).append(" ").append(solution(candies)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
