import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> a = new ArrayList<>();
			ArrayList<Integer> b = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) a.add(Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) b.add(Integer.parseInt(st.nextToken()));
			
			int answer = 0;
			int diff = Math.abs(a.size() - b.size());
			if(a.size() > b.size()) {
				for(int i = 0; i <= diff; i++) {
					int cur_sum = 0;
					for(int j = i; j < i + b.size(); j++) {
						cur_sum += a.get(j) * b.get(j - i);
					}
					answer = Math.max(answer, cur_sum);
				}
			}
			else {
				for(int i = 0; i <= diff; i++) {
					int cur_sum = 0;
					for(int j = i; j < i + a.size(); j++) {
						cur_sum += a.get(j - i) * b.get(j);
					}
					answer = Math.max(answer, cur_sum);
				}
			}
			
			bw.write(String.format("#%d %d\n", tc, answer));
			bw.flush();
		}
		
		bw.close();
	}
}
