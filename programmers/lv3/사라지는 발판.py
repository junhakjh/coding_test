def solution(board, aloc, bloc):
    answer, w, h = 0, len(board[0]), len(board)
    
    def find_route(x, y):
        dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
        arr = []
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < w and 0 <= ny < h and board[ny][nx] == 1:
                arr.append((nx, ny))
        return arr
    
    def dfs(ax, ay, bx, by, num):
        if num % 2 == 0:
            cx, cy = ax, ay
        else:
            cx, cy = bx, by
            
        candidate = find_route(cx, cy)
        if not candidate:
            return num % 2 != 0, num
        if [ax, ay] == [bx, by]:
            return num % 2 == 0, num + 1
        
        board[cy][cx] = 0
        win, lose = [], []
        for nx, ny in candidate:
            if num % 2 == 0:
                is_a_win, cnt = dfs(nx, ny, bx, by, num + 1)
                if is_a_win:
                    win.append(cnt)
                else:
                    lose.append(cnt)
            else:
                is_a_win, cnt = dfs(ax, ay, nx, ny, num + 1)
                if not is_a_win:
                    win.append(cnt)
                else:
                    lose.append(cnt)
        board[cy][cx] = 1
        if win:
            return num % 2 == 0, min(win)
        else:
            return num % 2 != 0, max(lose)
    
    return dfs(aloc[1], aloc[0], bloc[1], bloc[0], 0)[1]
