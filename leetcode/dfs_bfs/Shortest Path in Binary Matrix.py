from collections import deque

class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        num, queue, w, h = 1, deque([]), len(grid[0]), len(grid)
        dx, dy = [1, 1, 0, -1, -1, -1, 0, 1], [0, 1, 1, 1, 0, -1 ,-1, -1]
        if grid[0][0] == 0:
            queue.append([0, 0, num])
            grid[0][0] = 1
        while len(queue) > 0:
            x, y, cur_num = queue.popleft()
            if x == w - 1 and y == h - 1:
                return cur_num
            for i in range(8):
                nx, ny = x + dx[i], y + dy[i]
                if 0 <= nx < w and 0 <= ny < h:
                    if grid[ny][nx] == 0:
                        queue.append([nx, ny, cur_num + 1])
                        grid[ny][nx] = 1
        return -1
