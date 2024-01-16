from collections import deque

def solution(maps):
    answer, w, h = -1, len(maps[0]), len(maps)
    dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
    visited, queue, check = [[False for _ in range(w)] for _ in range(h)], deque([]), False
    for y in range(h):
        for x in range(w):
            if maps[y][x] == 'S':
                queue.append([x, y, 0])
                visited[y][x] = True
                check = True
                break
        if check: break
    
    has_key = False
    while len(queue) > 0:
        x, y, num = queue.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < w and 0 <= ny < h:
                if maps[ny][nx] == 'X' or visited[ny][nx]: continue
                if maps[ny][nx] == 'L':
                    visited = [[False for _ in range(w)] for _ in range(h)]
                    queue.clear()
                    queue.append([nx, ny, num + 1])
                    visited[ny][nx] = True
                    has_key = True
                    break
                elif maps[ny][nx] == 'O' or maps[ny][nx] == 'S':
                    queue.append([nx, ny, num + 1])
                    visited[ny][nx] = True
                else:
                    if has_key: return num + 1
                    else:
                        queue.append([nx, ny, num + 1])
                        visited[ny][nx] = True
    
    return answer
