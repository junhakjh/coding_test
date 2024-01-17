import sys 
sys.setrecursionlimit(10000)

def solution(maps):
    answer, dx, dy, w, h = [], [1, 0, -1, 0], [0, 1, 0, -1], len(maps[0]), len(maps)
    for i, row in enumerate(maps):
        maps[i] = list(row)
    
    num = 0
    def dfs(x, y):
        nonlocal num
        num += int(maps[y][x])
        maps[y][x] = 'X'
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < w and 0 <= ny < h:
                if maps[ny][nx].isdigit():
                    dfs(nx, ny)
    
    for y, row in enumerate(maps):
        for x, tile in enumerate(row):
            if tile.isdigit():
                dfs(x, y)
                answer.append(num)
                num = 0
    
    return  [-1] if len(answer) == 0 else sorted(answer)
