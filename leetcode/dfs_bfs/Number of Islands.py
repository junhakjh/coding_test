from collections import deque

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        answer, w, h, queue = 0, len(grid[0]), len(grid), deque([])
        dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
        for y in range(h):
            for x in range(w):
                if grid[y][x] == '1':
                    queue.append([x, y])
                    grid[y][x] = '0'
                    answer += 1
                while len(queue) > 0:
                    cur_x, cur_y = queue.popleft()
                    for i in range(4):
                        nx, ny = cur_x + dx[i], cur_y + dy[i]
                        if 0 <= nx < w and 0 <= ny < h:
                            if grid[ny][nx] == '1':
                                queue.append([nx, ny])
                                grid[ny][nx] = '0'
        return answer
