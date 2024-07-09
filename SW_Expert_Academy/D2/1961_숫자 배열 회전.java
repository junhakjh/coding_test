import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			ArrayList<ArrayList<String>> matrix = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				ArrayList<String> row = new ArrayList<>();
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) row.add(st.nextToken());
				matrix.add(row);
			}
			
			ArrayList<ArrayList<String>> answer = new ArrayList<>();
			for(int j = 0; j < n; j++) {
				ArrayList<String> row = new ArrayList<>();
				for(int k = 0; k < n; k++) row.add("");
				answer.add(row);
			}
			
			for(int i = 0; i < 3; i ++) {
				ArrayList<ArrayList<String>> rotated = new ArrayList<>();
				for(int j = 0; j < n; j++) {
					ArrayList<String> row = new ArrayList<>();
					for(int k = 0; k < n; k++) row.add("");
					rotated.add(row);
				}
				
				for(int y = 0; y < n; y++) {
					for(int x = 0; x < n; x++) {
						switch(i) {
							case 0:
								rotated.get(x).set(n - y - 1, matrix.get(y).get(x));
								break;
							case 1:
								rotated.get(n - y - 1).set(n - x - 1, matrix.get(y).get(x));
								break;
							default:
								rotated.get(n - x - 1).set(y, matrix.get(y).get(x));
								break;						
						}
					}
				}
				
				for(int j = 0; j < n; j++) {
					answer.get(j).set(i, String.join("", rotated.get(j)));
				}
				
			}
			
			String out = String.format("#%d\n", tc);
			for(int i = 0; i < n; i++) {
				out += String.join(" ", answer.get(i));
				out += "\n";
			}
			bw.write(out);
			bw.flush();
		}
		
		bw.close();
	}
}
