import java.util.*;
import java.io.*;

/**
 * 
 * @author 김준하
 * @since 2024. 8. 14.
 * @link https://www.acmicpc.net/problem/15650
 * @timecomplex O(n!)
 * @performance 14,216 kb, 100 ms
 * @category #dfs
 * @note
 */

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;
    static int n, m;
    static int[] arr;
    
    static void combination(int start, int curI) {
        if(curI == m) {
            for(int i = 0; i &lt; m; i++) {
                output.append(arr[i]).append(" ");
            }
            output.append("\n");
            return;
        }
        
        for(int i = start; i &lt;= n; i++) {
            arr[curI] = i;
            combination(i + 1, curI + 1);
        }
    }
    
    public static void main(String args[]) throws Exception {
        st = new StringTokenizer(input.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        
        combination(1, 0);

        System.out.print(output);
    }
}
