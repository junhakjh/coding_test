from collections import deque

dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]

def solution(game_board, table):
    answer = 0
    visited = [[False for _ in range(len(table[0]))] for _ in range(len(table))]
    
    def find_piece(table, x, y):
        nonlocal visited
        count = 0
        q, x1, x2, y1, y2 = deque([(x, y)]), x, x, y, y
        visited[y][x] = True
        while q:
            cur_x, cur_y = q.popleft()
            count += 1
            x1, x2, y1, y2 = min(x1, cur_x), max(x2, cur_x), min(y1, cur_y), max(y2, cur_y)
            for i in range(4):
                nx, ny = cur_x + dx[i], cur_y + dy[i]
                if 0 <= nx < len(table[0]) and 0 <= ny < len(table):
                    if not visited[ny][nx] and table[ny][nx] == 1:
                        visited[ny][nx] = True
                        q.append((nx, ny))
        
        arr = []
        for y in range(y1, y2 + 1):
            arr.append(table[y][x1:x2 + 1])
        
        return (arr, count)
    
    board_pieces, pieces = [], []
    
    for y in range(len(table)):
        for x in range(len(table[0])):
            if table[y][x] == 1 and not visited[y][x]:
                pieces.append(find_piece(table, x, y))
    
    for y in range(len(game_board)):
        for x in range(len(game_board[0])):
            game_board[y][x] = game_board[y][x] ^ 1
    
    visited = [[False for _ in range(len(game_board[0]))] for _ in range(len(game_board))]
    
    for y in range(len(game_board)):
        for x in range(len(game_board[0])):
            if game_board[y][x] == 1 and not visited[y][x]:
                board_pieces.append(find_piece(game_board, x, y))
    
    for board_piece, board_count in board_pieces:
        check = False
        for piece, count in pieces:
            if board_count != count: continue
            cp_piece = piece[:]
            for _ in range(4):
                if board_piece == cp_piece:
                    answer += count
                    pieces.remove((piece, count))
                    check = True
                    break
                cp_piece = rotate(cp_piece)
            if check: break
    
    return answer

def rotate(arr):
    rotated = [[0 for _ in range(len(arr))] for _ in range(len(arr[0]))]
    for y in range(len(arr)):
        for x in range(len(arr[0])):
            rotated[x][len(arr) - 1 - y] = arr[y][x]
    return rotated

