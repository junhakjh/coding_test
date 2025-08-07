import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        int prev = 0;
        for(int[] target: targets) {
            if(target[0] >= prev) {
                prev = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}
