class Solution {
    public String solution(int R, int C, int r, int c, int tr, int tc, int k) {
        StringBuilder answer = new StringBuilder();
        r--;
        c--;
        tr--;
        tc--;
        
        while(k-- > 0) {
            // 아래로 내려갈 수 있으면 무조건 아래로
            if(r < tr || (r + 1 < R && Math.abs((r + 1) - tr) + Math.abs(c - tc) <= k)) {
                r++;
                answer.append("d");
                continue;
            }
            // 왼쪽으로 갈 수 있으면 무조건 왼쪽으로
            if(tc < c || (c - 1 >= 0 && Math.abs(r - tr) + Math.abs((c - 1) - tc) <= k)) {
                c--;
                answer.append("l");
                continue;
            }
            // 오른쪽으로 갈 수 있으면 무조건 오른쪽으로
            if(c > tc || (c + 1 < C && Math.abs(r - tr) + Math.abs((c + 1) - tc) <= k)) {
                c++;
                answer.append("r");
                continue;
            }
            // d, l, r 다 안되면 어쩔 수 없이 u
            r--;
            if(r < 0) {
                break;
            }
            answer.append("u");
        }
        
        return r == tr && c == tc ? answer.toString() : "impossible";
    }
}
