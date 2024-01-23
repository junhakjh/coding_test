class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        w, h = len(board[0]), len(board)
        dx, dy, visited, answer = [1, 0, -1, 0], [0, 1, 0, -1], [[False for _ in range(w)] for _ in range(h)], False
        def dfs(x, y, cur_i):
            nonlocal visited, answer
            if cur_i == len(word) - 1:
                answer = True
                return
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if 0 <= nx < w and 0 <= ny < h:
                    if not visited[ny][nx] and board[ny][nx] == word[cur_i + 1]:
                        visited[ny][nx] = True
                        dfs(nx, ny, cur_i + 1)
                        visited[ny][nx] = False
            if answer:
                return
        for y in range(h):
            for x in range(w):
                if board[y][x] == word[0]:
                    visited[y][x] = True
                    dfs(x, y, 0)
                    visited = [[False for _ in range(w)] for _ in range(h)]
                if answer: return True

        return False
