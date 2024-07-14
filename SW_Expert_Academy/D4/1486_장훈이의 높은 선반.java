import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(input.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int answer = Integer.MAX_VALUE;

            int N; int B;
            ArrayList<Integer> heights = new ArrayList<>();

            st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken()); B = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(input.readLine());
            while(st.hasMoreTokens()) {
                int height = Integer.parseInt(st.nextToken());
                heights.add(height);
            }

            List<Integer> heightSums = new ArrayList<>();
            outer: for(int height: heights) {
                List<Integer> toAdd = new ArrayList<>(Collections.nCopies(1, height));
                for(int setHeight: heightSums) {
                    int heightSum = setHeight + height; int diff = heightSum - B;
                    if(diff == 0) {
                        answer = 0;
                        break outer;
                    }
                    if(diff > 0) answer = Math.min(answer, diff);
                    toAdd.add(heightSum);
                }
                heightSums.addAll(toAdd);
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
    }
}
