from collections import deque

n, m = map(int, input().split())
matrix = []
namu, exit, ghost, wall, empty = 'N', 'D', 'G', '#', '.'
for _ in range(n):
    matrix.append(list(input()))

dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]

def solution():
    namu_position, q = (), deque([])
    ghost_fp = [[float('inf')] * m for _ in range(n)]
    for y in range(n):
        for x in range(m):
            if matrix[y][x] == namu:
                namu_position = (x, y)
            if matrix[y][x] == ghost:
                q.append((x, y, 0))
                ghost_fp[y][x] = 0
    
    while q:
        cx, cy, cd = q.popleft()
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            if 0 <= nx < m and 0 <= ny < n and cd + 1 < ghost_fp[ny][nx]:
                ghost_fp[ny][nx] = min(ghost_fp[ny][nx], cd + 1)
                q.append((nx, ny, cd + 1))
    
    x, y = namu_position
    visited, q = [[False] * m for _ in range(n)], deque([(x, y, 0)])
    visited[y][x] = True
    while q:
        cx, cy, cd = q.popleft()
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            if 0 <= nx < m and 0 <= ny < n and not visited[ny][nx] and not matrix[ny][nx] == wall and cd + 1 < ghost_fp[ny][nx]:
                if matrix[ny][nx] == exit:
                    return True
                visited[ny][nx] = True
                q.append((nx, ny, cd + 1))
    
    return False

print('Yes' if solution() else 'No')
