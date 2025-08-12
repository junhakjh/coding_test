import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        int[][] minCosts = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                minCosts[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }
        
        for(int[] fare: fares) {
            int e1 = fare[0], e2 = fare[1], cost = fare[2];
            minCosts[e1][e2] = cost;
            minCosts[e2][e1] = cost;
        }
        
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(minCosts[i][k] == Integer.MAX_VALUE || minCosts[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    minCosts[i][j] = Math.min(minCosts[i][j], minCosts[i][k] + minCosts[k][j]);
                }
            }
        }
        
        for(int start = 1; start <= n; start++) {
            int[] row = minCosts[start];
            if(row[a] != Integer.MAX_VALUE && row[b] != Integer.MAX_VALUE && row[s] != Integer.MAX_VALUE) {
                answer = Math.min(answer, row[a] + row[b] + row[s]);
            }
        }
        
        return answer;
    }
}
