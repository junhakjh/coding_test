import java.util.*;

class Solution {
    int strToMin(String str) {
        String[] arr = str.split(":");
        return 60 * Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]);
    }
    
    String minToStr(int min) {
        int hh = min / 60, mm = min % 60;
        return new StringBuilder().append(String.format("%02d", hh)).append(":").append(String.format("%02d", mm)).toString();
    }
    
    public String solution(int n, int t, int m, String[] timeTable) {
        String answer = "";
        
        Queue<Integer> pq = new PriorityQueue<>();
        for(String timeStr: timeTable) {
            pq.offer(strToMin(timeStr));
        }
        
        int curMin = 60 * 9;
        for(int i = 0; i < n; i++) {
            int cap = m, lastMin = -1;
            while(!pq.isEmpty() && cap > 0) {
                int nextMin = pq.peek();
                if(nextMin > curMin) {
                    break;
                }
                lastMin = pq.poll();
                cap--;
            }
            if(i == n - 1) {
                if(cap == 0) {
                    answer = minToStr(lastMin - 1);
                } else {
                    answer = minToStr(curMin);
                }
            }
            
            curMin += t;
        }
        
        return answer;
    }
}
