class Solution {
    int getRowColNum(String[] board, int start, char target) {
        int rowNum = 0, colNum = 0;
        for(int i = 0; i < 3; i++) {
            if(board[i].charAt(start) == target) {
                rowNum++;
            }
            if(board[start].charAt(i) == target) {
                colNum++;
            }
        }
        
        return (rowNum == 3 ? 1 : 0) + (colNum == 3 ? 1 : 0);
    }
    int getDiagNum(String[] board, char target) {
        int down = 0, up = 0;
        for(int i = 0; i < 3; i++) {
            if(board[i].charAt(i) == target) {
                down++;
            }
            if(board[2 - i].charAt(i) == target) {
                up++;
            }
        }
        
        return (down == 3 ? 1 : 0) + (up == 3 ? 1 : 0);
    }
    
    public int solution(String[] board) {
        int answer = -1;
        
        int O = 0, X = 0;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                if(board[r].charAt(c) == 'O') {
                    O++;
                }
                if(board[r].charAt(c) == 'X') {
                    X++;
                }
            }
        }
        if(O - X > 1 || X > O) {
            return 0;
        }
        
        int o = 0, x = 0;
        o += getDiagNum(board, 'O');
        x += getDiagNum(board, 'X');
        for(int i = 0; i < 3; i++) {
            o += getRowColNum(board, i, 'O');
            x += getRowColNum(board, i, 'X');
        }
        
        return (O == X && o < x) || (O > X && o > x) || (o == 0 && x == 0) ? 1 : 0;
    }
}
