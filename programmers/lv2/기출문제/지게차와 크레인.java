class Solution {
    int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    boolean isIn(int r, int c, int R, int C) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
    
    void dfs(char character, String[] storage, int[][] visited, boolean[][] curVisited, int ri, int r, int c, int R, int C) {
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if(isIn(nr, nc, R, C) && !curVisited[nr][nc]) {
                if(storage[nr].charAt(nc) == character && visited[nr][nc] == 101) {
                    visited[nr][nc] = ri;
                }
                if(visited[nr][nc] < ri) {
                    curVisited[nr][nc] = true;
                    dfs(character, storage, visited, curVisited, ri, nr, nc, R, C);
                }
            }
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int R = storage.length, C = storage[0].length();
        
        int[][] visited = new int[R][C];
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                visited[r][c] = 101;
            }
        }
        
        for(int ri = 0; ri < requests.length; ri++) {
            String request = requests[ri];
            char character = request.charAt(0);
            if(request.length() == 1) {
                boolean[][] curVisited = new boolean[R][C];
                for(int r = 0; r < R; r++) {
                    if(storage[r].charAt(0) == character && visited[r][0] == 101) {
                        visited[r][0] = ri;
                    }
                    if(storage[r].charAt(C - 1) == character && visited[r][C - 1] == 101) {
                        visited[r][C - 1] = ri;
                    }
                    if(!curVisited[r][0] && visited[r][0] < ri) {
                        curVisited[r][0] = true;
                        dfs(character, storage, visited, curVisited, ri, r, 0, R, C);
                    }
                    if(!curVisited[r][C - 1] && visited[r][C - 1] < ri) {
                        curVisited[r][C - 1] = true;
                        dfs(character, storage, visited, curVisited, ri, r, C - 1, R, C);
                    }
                }
                for(int c = 0; c < C; c++) {
                    if(storage[0].charAt(c) == character && visited[0][c] == 101) {
                        visited[0][c] = ri;
                    }
                    if(storage[R - 1].charAt(c) == character && visited[R - 1][c] == 101) {
                        visited[R - 1][c] = ri;
                    }
                    if(!curVisited[0][c] && visited[0][c] < ri) {
                        curVisited[0][c] = true;
                        dfs(character, storage, visited, curVisited, ri, 0, c, R, C);
                    }
                    if(!curVisited[R - 1][c] && visited[R - 1][c] < ri) {
                        curVisited[R - 1][c] = true;
                        dfs(character, storage, visited, curVisited, ri, R - 1, c, R, C);
                    }
                }
            } else {
                for(int r = 0; r < R; r++) {
                    for(int c = 0; c < C; c++) {
                        if(storage[r].charAt(c) == character) {
                            visited[r][c] = ri;
                        }
                    }
                }
            }
        }
        
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(visited[r][c] == 101) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
