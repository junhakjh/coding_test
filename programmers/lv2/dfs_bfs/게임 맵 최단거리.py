from collections import deque

def solution(maps):
    answer, queue, maps[0][0], w, h = 0, deque([[0, 0, 1]]), 0, len(maps[0]), len(maps)
    dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
    while len(queue) > 0:
        x, y, num = queue.popleft()
        if x == w - 1 and y == h - 1:
            return num
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < w and 0 <= ny < h:
                if maps[ny][nx] == 1:
                    maps[ny][nx] = 0
                    queue.append([nx, ny, num + 1])
    
    return -1
