class Solution:
    def totalNQueens(self, n: int) -> int:
        answer = 0
        arr = [[0] * n for _ in range(n)]
        def q_check(x, y):
            for i in range(x):
                if arr[y][i]:
                    return False
                nx, ny = x - (i + 1), y - (i + 1)
                if ny >= 0 and arr[ny][nx]:
                    return False
                nx, ny = x - (i + 1), y + (i + 1)
                if ny < n and arr[ny][nx]:
                    return False
                    
            return True

        def dfs(x):
            nonlocal answer, arr
            if x == n:
                answer += 1
                return
            for y in range(n):
                if q_check(x, y):
                    arr[y][x] = 1
                    dfs(x + 1)
                    arr[y][x] = 0
        
        dfs(0)

        return answer
