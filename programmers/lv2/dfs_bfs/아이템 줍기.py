from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
    answer, graph = 0, [[-1 for _ in range(101)] for _ in range(101)]
    for r in rectangle:
        x1, y1, x2, y2 = map(lambda x: x * 2, r)
        for y in range(y1, y2 + 1):
            for x in range(x1, x2 + 1):
                if x1 < x < x2 and y1 < y < y2:
                    graph[y][x] = 0
                elif graph[y][x] != 0:
                    graph[y][x] = 1
    q = deque([(characterX * 2, characterY * 2, 0)])
    while q:
        x, y, num = q.popleft()
        if x == itemX * 2 and y == itemY * 2:
            return num // 2
        graph[y][x] = 0
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < 101 and 0 <= ny < 101:
                if graph[ny][nx] == 1:
                    q.append((nx, ny, num + 1))
    
    return answer
