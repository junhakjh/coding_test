ATTACK, RESTORE = 1, 2

def solution(board, skill):
    answer, w, h = 0, len(board[0]), len(board)
    
    arr = [[0 for _ in range(w + 1)] for _ in range(h + 1)]
    
    for s_type, r1, c1, r2, c2, degree in skill:
        if s_type == ATTACK:
            arr[r1][c1] -= degree
            arr[r1][c2 + 1] += degree
            arr[r2 + 1][c1] += degree
            arr[r2 + 1][c2 + 1] -= degree
        else:
            arr[r1][c1] += degree
            arr[r1][c2 + 1] -= degree
            arr[r2 + 1][c1] -= degree
            arr[r2 + 1][c2 + 1] += degree
                    
    for y in range(h + 1):
        for x in range(1, w + 1):
            arr[y][x] += arr[y][x - 1]
    for x in range(w + 1):
        for y in range(1, h + 1):
            arr[y][x] += arr[y - 1][x]
    
    for y in range(h):
        for x in range(w):
            if board[y][x] + arr[y][x] > 0:
                answer += 1
    
    return answer
