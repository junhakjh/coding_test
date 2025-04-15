import java.util.*;
import java.math.*;

class Solution {
    final int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    
    boolean isIn(int r, int c, int R, int C) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        int R = land.length, C = land[0].length;
        int[][] oilLand = new int[R][C];
        Map<Integer, Integer> oilMap = new HashMap<>();
        
        int landNum = 1;
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(land[r][c] == 1) {
                    land[r][c] = 0;
                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {r, c});
                    int oils = 0;
                    while(!q.isEmpty()) {
                        int[] item = q.poll();
                        int curR = item[0], curC = item[1];
                        oils++;
                        oilLand[curR][curC] = landNum;
                        for(int i = 0; i < 4; i++) {
                            int nr = curR + dr[i], nc = curC + dc[i];
                            if(isIn(nr, nc, R, C) && land[nr][nc] == 1) {
                                land[nr][nc] = 0;
                                q.offer(new int[] {nr, nc});
                            }
                        }
                    }
                    oilMap.put(landNum, oils);
                    landNum++;
                }
            }
        }
        
        for(int c = 0; c < C; c++) {
            Set<Integer> set = new HashSet<>();
            for(int r = 0; r < R; r++) {
                if(oilLand[r][c] != 0) {
                    set.add(oilLand[r][c]);
                }
            }
            int sum = 0;
            for(int key: set) {
                sum += oilMap.get(key);
            }
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
}
