import java.io.*;

public class Solution {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(input.readLine());
		
		for(int num = 1; num <= n; num++) {
			String str = Integer.toString(num);
			int cnt = 0;
			for(int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if(c == '3' || c == '6' || c == '9') {
					cnt++;
					sb.append("-");
				}
			}
			if(cnt == 0) {
				sb.append(str);
			}
			if(num != n) {
				sb.append(" ");
			}
		}
		
		System.out.println(sb.toString());
	}

}
