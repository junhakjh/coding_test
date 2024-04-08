def solution(board):
    answer, w, h = float('inf'), len(board[0]), len(board)
    dx, dy = [0, 1, 0, -1], [1, 0, -1, 0]
    cost_board = [[float('inf')] * w for _ in range(h)]
    
    def dfs(direction, x, y, matrix):
        nonlocal answer, cost_board
        if x == w - 1 and y == h - 1:
            answer = min(answer, cost_board[y][x])
            return
        
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < w and 0 <= ny < h and matrix[ny][nx] == 0:
                if (direction + i) % 2 == 1:
                    plus = 6
                else:
                    plus = 1
                if cost_board[ny][nx] + 4 < cost_board[y][x] + plus:
                    continue
                matrix[ny][nx], cost_board[ny][nx] = 1, cost_board[y][x] + plus
                dfs(i, nx, ny, matrix)
                matrix[ny][nx] = 0
    
    board[0][0], cost_board[0][0] = 1, 0
    
    for i in range(4):
        dfs(i, 0, 0, board)
    
    return answer * 100
