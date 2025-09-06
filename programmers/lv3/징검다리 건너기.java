import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int l = 0, r = 200_000_000;
        label: while(l < r) {
            int cap = (l + r) / 2;
            int max = 0, sum = 0;
            for(int stone: stones) {
                if(stone - cap > 0) {
                    max = Math.max(max, sum);
                    sum = 0;
                }
                if(stone - cap <= 0) {
                    sum++;
                    if(sum >= k) {
                        r = cap;
                        continue label;
                    }
                }
            }
            max = Math.max(max, sum);
            if(max >= k) {
                r = cap;
            } else {
                l = cap + 1;
            }
        }
        answer = l;
       
        return answer;
    }
}
