class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int i = 0; i < balls.length; i++) {
            int min = Integer.MAX_VALUE;
            
            int tx = balls[i][0], ty = balls[i][1];
            if(startY != ty || startX < tx) {
                min = Math.min(min, (int) Math.pow(tx + startX, 2) + (int) Math.pow(Math.abs(ty- startY), 2));
            }
            if(startY != ty || startX > tx) {
                min = Math.min(min, (int) Math.pow((m - tx) + (m - startX), 2) + (int) Math.pow(Math.abs(ty - startY), 2));
            }
            if(startX != tx || startY < ty) {
                min = Math.min(min, (int) Math.pow(ty + startY, 2) + (int) Math.pow(Math.abs(tx - startX), 2));
            }
            if(startX != tx || startY > ty) {
                min = Math.min(min, (int) Math.pow((n - ty) + (n - startY), 2) + (int) Math.pow(Math.abs(tx - startX), 2));
            }
            
            answer[i] = min;
        }
        
        return answer;
    }
}
