class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int dCap = 0, pCap = 0;
        for(int i = n - 1; i >= 0; i--) {
            dCap += deliveries[i];
            pCap += pickups[i];
            
            while(dCap > 0 || pCap > 0) {
                dCap -= cap;
                pCap -= cap;
                answer += (i + 1) * 2;
            }
        }
        
        return answer;
    }
}
